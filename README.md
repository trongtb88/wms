Requirements :
  - JDK 1.8 or later

### Features!

  - Authentication with WS
  - Get Date from WS
  - Send Data to COM RS 232

You can also:
  - Change WS URL, COM configuration from config.properties file
  - Change font style and font size for application
  - Tracking application running by reading log files.
### Installation

- Install maven to buld
- Install Jkd 1.8
- Change config file with correct URL

```sh
$ git clone https://github.com/trongtb88/wms
$ cd wms
$ mvn clean package
$ mkdir target/config
$ chmod 755 target/config
$ scp src/main/resources/* target/config
$ java -jar target/vog_app-1.0.0.jar
```

#### if you want to move vog_app-1.0.0.jar file to other location, please move also folder dependency-jars and config folder

