package me.lapis.firstplugin;

// java imports
import java.util.Map;
import java.util.logging.Logger;

// bukkit imports
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

// my code imports


public final class Main extends JavaPlugin {

    Logger bukkit_logger = getServer().getLogger();                             //get Server's logger
    PluginDescriptionFile pluginDescription = this.getDescription();            //get Plugin's description file
    ColorfulConsole colorfulConsole;                                            //get ConsoleSender & send colorized logs using it
    CommandManager cmdManager;                                                  //pre-initialized CommandManager class (to register this in several commands if possible)


    @Override
    public void onEnable() {
        /** public void onEnable()
         * Event called when plugin is enabled.
         *
         * { Process during onEnable() }
         * 1. print plugin information
         * 2. register Event Manager
         * 3. set Command Executors
         * 4. check Players currently online
         * */

        this.bukkit_logger.info( "플러그인의 콘솔 출력자를 생성합니다..");
        this.colorfulConsole = new ColorfulConsole(this.pluginDescription);
        this.bukkit_logger.info( "플러그인의 콘솔 출력자를 생성했습니다!");


        /* [ 플러그인 정보 출력 ]
         * 이 플러그인의 정보를 pluginDescription 을 통해 출력합니다.
         * */

        this.colorfulConsole.consoleColored(colorfulConsole.info,"플러그인의 정보 파일을 불러옵니다...");
        this.colorfulConsole.consoleColored(colorfulConsole.info, "====================================================");
        this.colorfulConsole.consoleColored(colorfulConsole.info, "Plugin Version : " + this.pluginDescription.getVersion());
        this.colorfulConsole.consoleColored(colorfulConsole.info, "Plugin API(Bukkit) Version : " + this.pluginDescription.getAPIVersion());
        this.colorfulConsole.consoleColored(colorfulConsole.info, "Plugin Authors : " + this.pluginDescription.getAuthors());
        this.colorfulConsole.consoleColored(colorfulConsole.info, "Plugin Website : " + this.pluginDescription.getWebsite());
        this.colorfulConsole.consoleColored(colorfulConsole.info, "====================================================");
        this.colorfulConsole.consoleColored(colorfulConsole.info,"플러그인의 정보 파일을 불러왔습니다!");

        /* [ 플러그인 이벤트 수신자 등록 ]
        * 이 플러그인의 이벤트 수신자 클래스인 me.lapis.firstplugin.EventManager 클래스를 버킷의 이벤트 수신자에 추가합니다.
        * */

        this.colorfulConsole.consoleColored(colorfulConsole.info, "[EventRegistration] 플러그인의 이벤트 수신 메소드를 등록하는 중입니다...");
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
        this.colorfulConsole.consoleColored(colorfulConsole.info, "[EventRegistration] 플러그인의 이벤트 수신 메소드를 등록했습니다!");


        /* [ 플러그인 커맨드 관리자 등록 ]
         * 이 플러그인의 커맨드 관리자 클래스인 me.lapis.firstplugin.CommandManager 클래스를 이 플러그인의 명령어들의 수신자에 추가합니다.
         * */

        this.colorfulConsole.consoleColored(colorfulConsole.debug,  "[CommandRegistration] Initializing CommandManager class w/ PluginDescription...");
        this.cmdManager = new CommandManager(this.colorfulConsole);

        Map<String, Map<String, Object>> commandsMap = this.pluginDescription.getCommands();
        this.colorfulConsole.consoleColored(colorfulConsole.debug,  "[CommandRegistration] Collecting commands Map keys");
        for(String key: commandsMap.keySet()){
            this.colorfulConsole.consoleColored(colorfulConsole.debug, "[CommandRegistration] key : " + key);
            getCommand(key).setExecutor(this.cmdManager);
        }


        /* [ 서버에 접속한 플레이어 확인 ]
         * 이 플러그인의 이벤트 수신자 클래스인 me.lapis.firstplugin.EventManager 클래스를 버킷의 이벤트 수신자에 추가합니다.
         * */

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            this.colorfulConsole.consoleColored(colorfulConsole.info, "[PlayerDetection] Found a Player in server : " + player.getDisplayName());
        }


        this.colorfulConsole.consoleColored(colorfulConsole.info, "플러그인이 활성화 되었습니다.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        /** public void onEnable()
         * Event called when plugin is enabled.
         *
         * { Process during onEnable() }
         * 1. print plugin information
         * 2. register Event Manager
         * 3. set Command Executors
         * 4. check Players currently online
         * */
        this.colorfulConsole.consoleColored(colorfulConsole.info, "플러그인이 비활성화 되었습니다.");
    }
}
