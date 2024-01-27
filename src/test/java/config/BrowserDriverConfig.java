package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${env}.properties"})
public interface BrowserDriverConfig extends Config {
    @Key("browser")
    String getBrowserName();
    @Key("version")
    String getBrowserVersion();
    @Key("remote")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub/")
    String getRemoteWebDriver();
}