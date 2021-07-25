package it.devfrqncy.sun.database;



import it.devfrqncy.sun.SUN;
import net.md_5.bungee.api.ProxyServer;
import java.sql.*;
import java.util.Properties;



public class MySql {

    private String host;
    private int port;
    private String dbname;

    private String user;
    private String password;


    private boolean useSSL;
    private boolean autoRECONNECT;

    private Connection c;

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private final static String CREATE_DATABASE = "CREATE DATABASE {DBNAME};";
    private final static String USE_DATABASE = "USE {DBNAME};";

    private final static String CREATE_TABLE_USERS = "CREATE TABLE  IF NOT EXISTS `users` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `uuid` varchar(36) NOT NULL,\n" +
            "  `username` varchar(100) NOT NULL,\n" +
            "  `password` varchar(255) DEFAULT NULL,\n" +
            "  `geyser` int(11) NOT NULL,\n" +
            "  `ip` varchar(20) NOT NULL,\n" +
            "  `paid` varchar(255) DEFAULT NULL,\n" +
            "   PRIMARY KEY (`id`),\n" +
            "   UNIQUE KEY `paid` (`paid`),\n" +
            "   KEY `uuid` (`uuid`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

    private final static String CREATE_TABLE_STAFF_2FA = "CREATE TABLE IF NOT EXISTS `staff_2fa` (\n" +
            "  `id` varchar(40) NOT NULL,\n" +
            "  `uuid` varchar(36) NOT NULL,\n" +
            "  `ip` varchar(16),\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

    private final static String CREATE_TABLE_WHITELIST_VPN = "CREATE TABLE  IF NOT EXISTS `vpn_whitelist` (\n" +
            "  `uuid` varchar(36) NOT NULL,\n" +
            "  PRIMARY KEY (`uuid`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

    public final static String ADD_WHITELIST_USER = "INSERT INTO `vpn_whitelist`(`uuid`) VALUES (?)";
    public final static String ADD_STAFF_2FA_USER = "INSERT INTO `staff_2fa`(`id`, `uuid`, `ip`) VALUES (?,?,?)";
    public final static String REGISTER_USER = "INSERT INTO `users`(`id`, `uuid`, `username`, `password`, `geyser`, `ip`, `paid`) VALUES (NULL,?,?,?,?,?,?)";

    public final static String DEL_WHITELIST = "DELETE FROM `vpn_whitelist` WHERE 1";
    public final static String DEL_STAFF_2FA = "DELETE FROM `staff_2fa` WHERE 1";

    public final static String GET_STAFF_2FA = "SELECT * FROM `staff_2fa` WHERE 1";
    public final static String GET_WHITELIST = "SELECT * FROM `vpn_whitelist` WHERE 1";

    public final static String GET_REGISTRED_USER = "SELECT * FROM `users` WHERE `uuid`= ?";


    public MySql(String host,int port,String dbname,String user,String password,boolean useSSL,boolean autoRECONNECT){
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
        this.useSSL = useSSL;
        this.autoRECONNECT = autoRECONNECT;
        openConnection();
        loadDatabase();
    }

    private void openConnection(){
        try{
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", String.valueOf(useSSL));
            properties.setProperty("autoReconnect", String.valueOf(autoRECONNECT));
            c = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/",properties);
        }catch(SQLException ex){
            // errore
            SUN.getInstance().getLogger().severe("connessione al db non riuscita");
            ProxyServer.getInstance().stop();
        }
    }

    private boolean checkDatabase(){
        /* recupera tutti database e torna vero se trova nella lista il DBNAME */
        ResultSet rs = null;
        try {
            rs = c.getMetaData().getCatalogs();
            while(rs.next())
                if(dbname.equals(rs.getString(1))) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadDatabase() {
        try {
            PreparedStatement pstmt = null;
            // se il database non esiste lo crea
            if(!checkDatabase()){
                pstmt = c.prepareStatement(CREATE_DATABASE.replace("{DBNAME}",dbname));
                pstmt.execute();
                System.out.print("database creato");
            }
            // seleziona il database da usare
            pstmt = c.prepareStatement(USE_DATABASE.replace("{DBNAME}",dbname));
            pstmt.execute();
            System.out.print("Database selezionato");
            // cotrolla se ci sono le tabelle e se non ci sono creale
            pstmt = c.prepareStatement(CREATE_TABLE_USERS);
            pstmt.execute();
            System.out.print("Tabella users caricata");

            /*if(SUN.getConfigYml().getS2FA().isEnabled()){
                pstmt = c.prepareStatement(CREATE_TABLE_STAFF_2FA);
                pstmt.execute();
                System.out.println("Tabella STAFF2FA caricata");
            }*/

            if(SUN.getConfigYml().getAntiVPN()){
                pstmt = c.prepareStatement(CREATE_TABLE_WHITELIST_VPN);
                pstmt.execute();
                System.out.print("Tabella VPN whitelist caricata");
            }
        } catch (SQLException e) {
            System.out.print("creazione tabelle non riuscita");
        }
    }

    public Connection getConnection(){ return c; }
}
