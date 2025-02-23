/**
 * 
 */
package com.login.common;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * Clase especializada en leer propiedades del sistema.
 */
public final class Env {

    /**
     * Objeto para albergar las propiedades del sistema.
     */
    private static PropertiesConfiguration configuration = null;
    static {
        try {
            configuration = new PropertiesConfiguration(
                    String.format("%s/%s.properties", App.PROPERTIES_PATH, App.COMPONENT_NAME));
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
     * 
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getProperty(String key) {
        return configuration.getString(key);
    }

    /**
     * Método para leer una propiedad obligatoria del sistema por Instancia.
     * 
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getPropertyByInst(String key) {
        String instanceKey = String.format("%s.%s", App.INSTANCE, key);
        String property = (String) configuration.getString(instanceKey);
        if (property == null || property.trim().isEmpty()) {
            throw new RuntimeException("No se encontró la propiedad: " + instanceKey);
        }
        return property;
    }

    /**
     * Método para leer una propiedad opcional del sistema por Instancia.
     * 
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getPropertyByInstOptional(String key) {
        String property = String.format("%s.%s", App.INSTANCE, key);
        return (String) configuration.getString(property);
    }
    
    /**
     * Método para leer una propiedad múltiple en formato value1, value2, value3 por Instancia.
     * 
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getPropertyMultiByInst(String key) {
        String property = String.format("%s.%s", App.INSTANCE, key);
        List<Object> list = configuration.getList(property);
        String valor = "";
        for (int i = 0; i < list.size(); i++) {
            valor += list.get(i);
            if (i != list.size() - 1) {
                valor += ",";
            }
        }
        return valor;
    }

    /**
     * Método para leer una propiedad obligatoria del sistema por Prefijo.
     * @param prefix Prefijo de la propiedad.
     * @param key Llave de la propiedad.
     * @return Valor de la propiedad.
     */
    public static String getPropertyByPrefix(String prefix, String key) {
        String instanceKey = String.format("%s.%s.%s", App.INSTANCE, prefix, key);
        String property = (String) configuration.getString(instanceKey);
        if (property == null || property.trim().isEmpty()) {
            throw new RuntimeException("No se encontró la propiedad: " + instanceKey);
        }
        return property;
    }

}
