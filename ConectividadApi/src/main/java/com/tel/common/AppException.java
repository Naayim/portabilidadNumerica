/**
 * 
 */
package com.tel.common;

/**
 * Excepción non-checked para la aplicación. No obliga a usar try-catch ni throws.
 */
public class AppException extends RuntimeException {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que recibe una checked exception.
     * @param e Excepción que se quiere manejar con try-catch o throws.
     */
    public AppException(Exception e) {
        super(e); // Invoca al constructor de la clase padre: RuntimeException.
    }

}
