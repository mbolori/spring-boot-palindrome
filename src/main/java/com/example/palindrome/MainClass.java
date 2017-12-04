package com.example.palindrome;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Starter class: Spring boot entry point.
 * It also defines the init of the hierarchy of all classed being scanned by Spring (labeled as @SprinBootApplication)
 * Features:
 * Create pidfile on working dir when starting.
 * if pidfile is present, application cannot start
 * if pidfile is removed, application will be stopped
 * 
 * TODO: Below features should be moved to a parent project (with common dependencies included)
 */
@SpringBootApplication
@EnableEurekaClient
public class MainClass {

    /** The Constant APPLICATION_PID_FILENAME. */
    public static final String APPLICATION_PID_FILENAME = "application.pid";
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String []args) {
        System.out.println("Starting app...");
        File pidFile = new File(APPLICATION_PID_FILENAME);
        if (pidFile.exists()) {
            System.out.println("App is running: application.pid exists.");
            return;
        }
        
        PidWatcher pidFileWatcher = new PidWatcher(pidFile);
        SpringApplication app = new SpringApplication(MainClass.class);
        app.setWebEnvironment(true);                        // run on web app env
        app.addListeners(new ApplicationPidFileWriter());   //create pid file...
        app.addListeners(pidFileWatcher);                   //add our PidWatcher as  app event listener ..
        
        ConfigurableApplicationContext ctx = app.run(args);
        pidFileWatcher.setApplicationContext(ctx);
    }
    
    /**
     * Watch over pidfile. If there is a delete event: Spring application stops gracefully.
     * Listen to "ApplicationReadyEvent" to setup the watcher on the file.
     * Implemented as Runnable: Watch is working on its own thread, this prevent spring app from stalling on the infinite loop waiting for file events.
     *
     */
    public static class PidWatcher implements ApplicationListener<SpringApplicationEvent>, Runnable{
     
        private File pidFile = null;
        
        private ApplicationContext context = null; 
        
        /**
         * Instantiates a new pid watcher.
         *
         * @param pid the pid
         */
        public PidWatcher(File pid) {
            this.pidFile = pid;
        }


        /**
         * When app is ready (fully started) set up WatchService for deletion events on pidfile.
         *
         * @param event the event
         * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
         */
        @Override
        public void onApplicationEvent(SpringApplicationEvent event) {
            if (event instanceof ApplicationReadyEvent) {
                System.out.println("Application is ready. Seting up Watcher on pidfile.");
                //start pid file watcher
                Thread watcherThread = new Thread(this);
                watcherThread.setDaemon(true);      //background thread(daemon thread), jvm can stop if only daemon threads keep running.
                watcherThread.setName("PidFileWatcher");
                watcherThread.start();
            }
        }
        
        /**
         * Sets the application context.
         *
         * @param context the new application context
         */
        public void setApplicationContext(ApplicationContext context) {
            this.context = context;
        }
        
        /**
         * Run.
         *
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            try {
                pidFile.deleteOnExit();
                WatchService watcher = FileSystems.getDefault().newWatchService(); //get watcher service
                
                Path myDir = Paths.get(".");
                myDir.register(watcher, StandardWatchEventKinds.ENTRY_DELETE);  //register a watcher on this directory for deletion events ..
                
                for (;;) {
                    WatchKey watckKey = watcher.take();  // wait for events on the watcher
                    List<WatchEvent<?>> events = watckKey.pollEvents();
                    for (WatchEvent<?> event : events) {
                        //if deleted our pidfile .. exit spring boot app gracefully
                        if ( event.kind() == StandardWatchEventKinds.ENTRY_DELETE && pidFile.getPath().equals((event.context().toString()))) {
                            System.out.println("Exiting.");
                            SpringApplication.exit(context, () -> {return 0;});  // if deleting pidfile ... return 0
                        }
                    }
                    watckKey.reset();  //GET rid of registered events..
                }
                
            } catch (Exception ex) {
               System.err.println("Error handling file events on pidfile");
            }
        }
    }
}
