package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${env}.properties"})
public interface BrowserDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    String getBrowserName();
    @Key("version")
    @DefaultValue("122")
    String getBrowserVersion();
    @Key("remote")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub/")
    String getRemoteWebDriver();
    @Key("base_url")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();
}