/**
 * Copyrigth 2024 Hitss.
 */
package com.login.common;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;

/**
 * Clase para configuración de Log4j2.
 */
public class LogConfig {

    /** Variable de contexto component. */
    private static final String COMPONENT = "component";

    /** Variable de contexto transactionId. */
    private static final String TRANSACTION_ID = "transactionId";

    /** No permite instancias. */
    private LogConfig() {
    }

    /**
     * Inicializa el contexto de Log4j2 por defecto.
     */
    public static void initLog4j2() {
        ThreadContext.put(COMPONENT, App.COMPONENT_NAME);
        ThreadContext.put(TRANSACTION_ID, UUID.randomUUID().toString());
    }

    /**
     * Inicializa el contexto de Log4j2 con parámetros personalizados.
     * @param component Nombre del componente a usar en la bitácora.
     * @param transactionId Id de transacción a usar en la bitácora.
     */
    public static synchronized void initLog4j2(String component, String transactionId) {
        ThreadContext.put(COMPONENT, component);
        ThreadContext.put(TRANSACTION_ID, transactionId);
    }
}
