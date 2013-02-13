/**
 * Copyright (c) 2009 Mark S. Kolich
 * http://mark.koli.ch
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
*/

package code;

import java.io.File;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class RollingDataFileAppender{
  
  // The default log level is INFO.
  private static final Level DEFAULT_LOGGER_LEVEL = Level.INFO;
  
  private static final String LOG_DIR = "data";
  private static final String LOG_FILENAME = "tweets.log";
  
  private static final String FILE_SEPARATOR_PROP = "file.separator";
  private static final String SYSTEM_OUT = "System.out";
  
  // This is the pattern that Log4j will use when logging:
  // %d{ISO8601} %5p %c{1}:%L - %m%n
  private static final String LOG_PATTERN = "%m%n";//"%d{ISO8601} %5p [%l] - %m%n";
  
  // Set the maximum size that each log file is allowed to reach before being
  // rolled over to backup files. In configuration files, the MaxFileSize option
  // takes an long integer in the range 0 - 2^63. You can specify the value with
  // the suffixes "KB", "MB" or "GB" so that the integer is interpreted being
  // expressed respectively in kilobytes, megabytes or gigabytes. For example,
  // the value "10KB" will be interpreted as 10240. 
  private static final int MAX_LOG_BACKUP_FILES = 200;
  private static final String MAX_LOG_FILE_SIZE = "65536KB"; 
  private static final int LOG_IO_BUFFER_SIZE_BYTES = 1024;
  
  // We use the root logger for everything so we can capture all of the output
  // from shared libraries that use Log4j too
  public static final Logger logger = Logger.getRootLogger();
  static {
    try {
      
      // Where the logs will go.
      final File logDir = new File( LOG_DIR );
      
      // Try to create the default log directory.
      logDir.mkdirs();
      
      final File logFile = new File( logDir,
              String.format("%s%s",
                    System.getProperty(FILE_SEPARATOR_PROP),
                    LOG_FILENAME) );
      
      // Create a new pattern layout with our requested log pattern.
      final PatternLayout pl = new PatternLayout( LOG_PATTERN );
      
      // Setup the rolling log file appender. Note the last arg, true,
      // which tells this RollingFileAppender to append to existing logs
      // instead of overwriting what's already there.
      final RollingFileAppender rfp = new RollingFileAppender( pl,
                          logFile.getCanonicalPath(),
                          true );
      
      // We want the logger to flush its output to the log file
      // stream immeaditely; if you don't have this set, then
      // Log4j will buffer the log file output which isn't ideal.
      rfp.setImmediateFlush( true );
      rfp.setBufferedIO( false );
      rfp.setBufferSize( LOG_IO_BUFFER_SIZE_BYTES );

      // Set the Max number of files and max size of each log
      // file to keep around.
      rfp.setMaxBackupIndex( MAX_LOG_BACKUP_FILES );
      rfp.setMaxFileSize( MAX_LOG_FILE_SIZE );
      
      // Set the default level of this logger.
      logger.setLevel( DEFAULT_LOGGER_LEVEL );
      
      // This logger will use the rolling appender.
      logger.addAppender( rfp );
      
      // Also log to the console so we can see what's going on.
     // final ConsoleAppender cp = new ConsoleAppender( pl, SYSTEM_OUT );
     // cp.setImmediateFlush( true );
     //       logger.addAppender( cp );
            
    }
    catch ( Exception e ) {
      // If something bad happened, log the exception
      // to System.err
      e.printStackTrace( System.err );
    }
  }
  
  
  public static void writeEvent(String s) {
	  logger.info(s);
  }
  /**
   * Usage examples.
   * 
   * @param args
   */
  /*public static void main ( String [] args ) {
    
    // Log an info message.
    logger.info( "Some info message" );
    
    // Log an error
    logger.error( "Ouch, something bad happened." );
    
    // Trace and debug messages, note that these WONT show up
    // because the default logging level is
    // INFO. If you want to see these types of messages
    // then you should set the DEFAULT_LOGGER_LEVEL to
    // Level.TRACE or Level.DEBUG
    logger.debug( "A debug message; DEFAULT_LOGGER_LEVEL = Level.INFO" );
    logger.trace( "A trace message; DEFAULT_LOGGER_LEVEL = Level.INFO" );
    
    // Log a bunch of random stuff, this should force the log file
    // to roll over at least a few times.
    for(int i = 0; i < 5000; i++){
      logger.info( "Epoch is " + new Date().getTime() );
    }
    
    // Foo bar fatal
    logger.fatal( "Foo bar" );
    
    logger.info( "Example ran successfully" );
    
  }*/

}