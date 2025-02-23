/**
 * 
 */
package com.tel.common;

import java.nio.charset.StandardCharsets;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * Clase especializada con constantes generales del sistema.
 */
public final class App {

    /** Llave para obtener el nombre del componente. */
    private static final String COMPONENT_NAME_KEY = "spring.application.name";

    /** Llave para leer el nombre del archivo de propiedades. */
    private static final String APPLICATION_PROPERTIES = "application.properties";

    /**
     * Objeto para albergar las propiedades del sistema.
     */
    private static PropertiesConfiguration configuration = null;
    
    static {
        try {
            configuration = new PropertiesConfiguration(APPLICATION_PROPERTIES);
            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            configuration.setAutoSave(true);
            configuration.setDelimiterParsingDisabled(true);
            configuration.setEncoding(StandardCharsets.UTF_8.toString());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para leer una propiedad del sistema.
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getProperty(String key) {
        return configuration.getString(key);
    }

    /************************************************************************************************
     * Constantes PRIVADAS exclusivas para esta clase.
     ************************************************************************************************/
    /** Llave para obtener la propiedad de sistema que contiene el path del archivo de propiedades. */
    private static final String PROPERTIES_PATH_KEY = "PROPERTIES_PATH";

    /** Llave para obtener la propiedad de sistema que contiene el path del archivo de bitácora. */
    private static final String LOGS_PATH_KEY = "LOGS_PATH";

    /** Llave para obtener la propiedad de sistema que contiene el nombre de instancia. */
    public static final String INSTANCE_KEY = "INSTANCE_NAME";

    /************************************************************************************************
     * Constantes PUBLICA PARA TODA LA APLICACION.
     ************************************************************************************************/
    /** Nombre del componente. */
    public static final String COMPONENT_NAME = (String) configuration.getProperty(COMPONENT_NAME_KEY);
    
    /** Constante que contiene el path del archivo de propiedades. */
    public static final String PROPERTIES_PATH = System.getProperty(PROPERTIES_PATH_KEY);

    /** Constante que contiene el path del archivo de bitácora. */
    public static final String LOGS_PATH = System.getProperty(LOGS_PATH_KEY);

    /** Constante que contiene el nombre de la instancia corriendo. */
    public static final String INSTANCE = System.getProperty(INSTANCE_KEY);

}