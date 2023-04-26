# Selenium
- open source web testing framework
- "de facto" web automation library

---
## Selenium in a Nutshell

### Selenium IDE  (Dead) 
- Firefox plugin impl'ing the *Record and Playback (R&P)* pattern
for web apps.
  - records manual interactions w/ Firefox and playback that
    recording in an automated fashion
    
### Selenium RC (Remote Control)  (also Dead)
- capable of driving different types of browsers automagically using
different languages
    - (Java, C#< Python, Ruby, PHP, Perl, js)


- injected **Selenium Core** into the SUT 
    - JS library controlled w/ an intermediate component called
    Selenium RC Server which rcv'd requests from test code.
      
- deprecated in 2016 due to security problems based on *same-origin policy*

![SeleniumRC](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SeleniumRC.png)

### Selenium WebDriver (Not Dead!)
- similar to RC, but it makes calls to the browser using each
browser's native support for automation 
    - (this differentiates it from RC, which used "same-origin")
  
- language bindings ("Text") from WebDriver communicate w/ a browser-specific
binary, which acts as a bridge to the real browser
    - EX:
      - *chromedriver* (Chrome) https://sites.google.com/a/chromium.org/chromedriver/
      - *geckodriver* (Firefox) https://github.com/mozilla/geckodriver
    - comms between Test and the drive are don ew/ JSON messages over HTTP
        - uses "JSON Wire Protocol"

- web driver is now a standardized mechanism (W3C WebDriver API)
    - https://www.w3.org/TR/webdriver
    
![SeleniumWebDriverSchema](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SeleniumWebDriverSchema.png)


### Selenium Grid (Not Dead!)
- extension of WebDriver
    - allows distributing browser exec on remote machines.


- a number of Nodes
    - different OS
    - different browsers
    - "Hub" Server keeps tracks of the nodes and proxies requests to
    them
      
![SeleniumGridSchema](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SeleniumGridSchema.png)


### WebDriver API Main Features

| WebDriver Feature | Description | Example |
| --- | --- | --- | 
| WebDriver object creation | allows to create WebDriver instances, which are used from test code to control a browser remotely | WebDriver driver = new FirefoxDriver();<br /> WebDriver driver = new ChromeDriver();<br />  WebDriver driver = new OperaDriver(); |
| Navigation | allows to navigate to given url | driver.get("http://emangini.com") |
| Locate Elements | allows to ID elements w/ a web page (WebElement) using different strategies (by id, name, class name, CSS Selector, link test, tag name or XPath) | WebElement webElement = driver.findElement(By.id("id"))<br />  driver.findElement(By.name("name"));<br /> driver.findElement(By.className("class"));<br /> driver.findElement(By.cssSelector("cssInput"));<br /> driver.findElement(By.linkTest("text"));<br /> driver.findElement(By.tagName("tag name"));<br /> driver.findElement(By.xpath("/html/body/div[4]");<br /> |
| Interact w/ Elements | from a given WebElement we can carry out different types of automated interactions (click elements, type text, clear input fields, read attrs, etc.) | webElement.click();<br /> webElement.sendKeys("text");<br /> webElement.clear();<br /> String text = webElement.getTest();<br /> String href = webElement.getAttribute("href");<br /> String css = webElement.getCssValue("css");<br /> Dimension dimension = webElement.getSize();<br /> boolean enabled = webElement.isEnabled();<br /> boolean selected = webElement.isSelected();<br /> boolean displayed = webElement.isDisplayed(); |
| Handle Waits | WebDriver can handle waits implicitly & explicitly | // Explicit<br /> WebDriverWait wait = new WebDriverWait(driver, 30); <br /> wait.until(somethingHappens);<br /> // Implicit <br /> driver.manage().timeouts.().implicitlyWait(30, SECONDS);

#### XPath
- XML Path Language
  - language to build expressions to parse/process XML-like documents (i.e. HTML)

---
## JUnit 5 Extension for Selenium
- *selenium-jupiter*
  - JUnit5 Extension
  - built using DI capabilities by extension model of JUnit5 Platform
  - supports the injection of different types of objects into @Test methods as params
  - allows injection of **WebDriver** subtypes
  
---
### Dependency Import

    pom.xml (Maven)

      <dependency>
          <groupId>io.github.bonigarcia</groupId>
          <artifactId>selenium-jupiter</artifactId>
          <version>${selenium-jupiter.version}<?version>
      </dependency>

- NOTE: The author of the book (Boni Garcia) is the provider of the *selenium-jupiter* module.

#### Transitive Dependencies
- **Selenium-java** 
  - (*org.seleniumhq.selenium:selenium-java*)
  - Java lib for Selenium WebDriver
  

- **WebDriverManager**
  - (*io.github.bonigarcia:webdrivermanager*)
  - Java lib for automatic Selenium WebDriver binaries mgmt in runtime for Java
  

- **Appium**
  - *io.appium:java-client*
  - Java client for Appium, a testing framework that extends Selenium to automate testing
  of native, hybrid and mobile web apps.
---
### Using Selenium Extension
- class level annotation 
  - **@ExtendWith(SeleniumExtension.class)**
  - @Test methods have to be provided 1+ params
    - impl WebDriver interface
    - provide *selenium-jupiter* control of the WebDriver object's lifecycle internally
  
  
#### WebDriver Subtypes

| Type | What it controls |
| --- | --- | 
| **ChromeDriver** | Google Chrome | 
| **FirefoxDriver** | Mozilla Firefox | 
| **EdgeDriver** | Microsoft Edge | 
| **OperaDriver** | Opera |
| **SafariDriver** | Apple Safari |
| **HtmlUnitDriver** | controls HtmlUnit, a headless browser that has no Gui) |
| **PhantomJSDriver** | Headless, PhantomJS |
| **InternetExplorerDriver** | ??? It's dead |
| **RemoteWebDriver** | used by Selenium Grid to control remote browsers |
| **AppiumDriver** | used to control mobile devices (Android, iOS)|
---

### WebDriver Testing
- see *example.junit5Selenium.LocalWebDriverTest
  - this executes an isolated chrome test, a compouned test using opera and firefox
  and then a compound headless browser test of PhantomJs and HtmlUnit.
  - NOTE: 
    - for REAL browsers, the browsers MUST be installed on the local system being tested.
    - headless browsers are consumed as java deps. 
  
### Grid Testing
- uses *selenium-jupiter* extension
  - argument to the test method is annotated
    - **@DriverUrl** (specifies the Selenium Server/Hub URL)
    - **@DriverCapabilities** (specifies the required capabilities for the test)

#### Launch a local Selenium Server (this emulates a remote server)
- go to **docker-compose.yml** in the root of this project
  - select the "play" button in the "services" node in the yaml file and start the local selenium grid.
  - in your browser go to localhost:4444
    - you'll see the selenium grid "page" for your local selenium server
  - select sessions
  
#### Running the test
- run *example.junit5Selenium.RemoteWebDriverTest*
  - in the browser window you should see the session pop up
  - (I put a **Thread.sleep** in the rest so it hangs out long enough. )
  
  
    NOTE: 
      
        You'll see some information from the Overview page too, but it's a bit
        more high-level. 

### Appium Testing
- this is a pain in the ass. 
  - 1.) if you have any javascript volumes (like the onshape crap), unmount them. 
  These prevent the local AppiumDriver from working properly
  - 2.) npm install -g appium appium-doctor
  - 3.) run appium-doctor --android 
      - and make sure all of the android dependencies are met.
  - 4.) Go into Android Studio and set up an AVD profile
      - get the VERSION of Chrome running on this AVD
  - 5.) Set up the correct chromedriver
      - go to chromedriver.storage.googleapis.com and get the "closest" 
      VERSION of chrome that matches the one from your AVID
      - the first xx.xx.xx numbers usually match.
      - make sure there is a ./chromedriver_mac64.zip
      - run npm install -g appium-chromedriver --chromedriver_version=\<VERSION>
  - 5.) cd to your Android SDK Root directory and execute
    - ./emulator -netdelay none -netspeed full -avd Pixel_4_API_30
    - (where the last argument is the AVD name)
  - 6.) Run the test.
  
        
- You can verify all of this by running the Appium Desktop app
  - make sure that ANDROID_SDK_ROOT is set
  - make sure your desired capabilities match at minimum below
    

    {
        "platformName": "Android",
        "browserName": "Chrome"
    }
  

- NOTE:
  - you may need to go in manually and figure out the elements on the NATIVE_APP
  before performing the actual test. 
    - the findElement() examples from the book are apparently tied to an old version
    of Chrome. 