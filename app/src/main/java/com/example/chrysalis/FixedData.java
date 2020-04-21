package com.example.chrysalis;

import java.io.File;

import static com.example.chrysalis.Activities.Load_Activity.AppBaseDir;

public class FixedData {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DIRECTORIES DATA
    public static final String BAR = File.separator;
    public static final String DIR_MASTER = AppBaseDir + BAR + "EEvaApp";
    public static final String DIR_CONFIG = DIR_MASTER + BAR + "Config";
    public static final String DIR_XTRAS = DIR_MASTER + BAR + "Xtras";
    public static final String DIR_DATAS = DIR_MASTER + BAR + "Datas";
    public static final String DIR_IMAGES = DIR_DATAS + BAR + "Images";
    public static final String DIR_WORK = DIR_MASTER + BAR + "Work";
    public static final String DIR_ZIPPED = DIR_MASTER + BAR + "Zipped";
    public static final String DIR_UNZIPPED = DIR_MASTER + BAR + "Unzipped";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ARCHIVOS JSON
    public static final String CONFIG_JSON = DIR_CONFIG + BAR + "config.json";
    public static final String APPDATA_JSON = DIR_CONFIG + BAR + "appData.json";
    public static final String DATAS_JSON = DIR_CONFIG + BAR + "datas.json";
    public static final String IDIOMAS_JSON = DIR_XTRAS + BAR + "Idiomas.json";
    public static final String DELEGACIONES_JSON = DIR_XTRAS + BAR + "Delegaciones.json";
    public static final String COMUNIDADES_JSON = DIR_XTRAS + BAR + "Comunidades.json";
    public static final String PROVINCIAS_JSON = DIR_XTRAS + BAR + "Provincias.json";
    public static final String EVENTOS_JSON = DIR_DATAS + BAR + "Eventos.json";
    public static final String DATOSINTERES_JSON = DIR_DATAS + BAR + "DatosInteres.json";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DIRECTORIES ZIP
    public static final String ZIP_MASTER = DIR_UNZIPPED + BAR + "EEvaApp";
    public static final String ZIP_CONFIG = ZIP_MASTER + BAR + "Config";
    public static final String ZIP_XTRAS = ZIP_MASTER + BAR + "Xtras";
    public static final String ZIP_DATAS = ZIP_MASTER + BAR + "Datas";
    public static final String ZIP_IMAGES = ZIP_DATAS + BAR + "Images";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ARCHIVOS ZIP
    public static final String CONFIG_ZIP = ZIP_CONFIG + BAR + "config.json";
    public static final String APPDATA_ZIP = ZIP_CONFIG + BAR + "appData.json";
    public static final String DATAS_ZIP = ZIP_CONFIG + BAR + "datas.json";
    public static final String IDIOMAS_ZIP = ZIP_XTRAS + BAR + "Idiomas.json";
    public static final String DELEGACIONES_ZIP = ZIP_XTRAS + BAR + "Delegaciones.json";
    public static final String COMUNIDADES_ZIP = ZIP_XTRAS + BAR + "Comunidades.json";
    public static final String PROVINCIAS_ZIP = ZIP_XTRAS + BAR + "Provincias.json";
    public static final String EVENTOS_ZIP = ZIP_MASTER + BAR + "Eventos.json";
    public static final String DATOSINTERES_ZIP = ZIP_MASTER + BAR + "DatosInteres.json";

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - API URL DATA
    public static final String URL_ACTIVATE = "http://api.eevapp.es/api/APPCTIVE/active/";
    public static final String URL_UPDATE = "http://api.eevapp.es/api/APPDATE/update/";
    public static final String URL_ASSITENTS = "http://api.eevapp.es/api/APPSISTENT/assistents/";
    public static final String URL_DATEREQUEST = "http://api.eevapp.es/api/REQDATEA/requestdata/";
}
