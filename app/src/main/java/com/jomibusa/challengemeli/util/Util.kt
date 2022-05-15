package com.jomibusa.challengemeli.util

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * @author Jomibusa
 */

object Util {

    /**
     * Función utilizada para reemplazar la primera coincidencia de http en una url por https
     * esto debido a que de lo contrario no se permite visualizar/cargar la imágen que viene en
     * la url del consumo del api de Mercado Libre
     * @param url Es la url a la cual se le va a buscar la coincidencia para luego ser reemplazada
     */
    fun replaceUrl(url: String): String {
        return url.replaceFirst("http", "https")
    }

    /**
     * Función utilizada para obtener un formato numérico de precio válido, ya que el formato real
     * que llega desde el consumo es un tipo Double, por lo cual no es muy entendible para el usuario
     * por lo que se hace la conversión a un formato válido
     * @param price Es el precio al cual se le va a aplicar el formato adecuado.
     */
    fun parseToCurrencyFormat(price: Double): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(price)
    }

}