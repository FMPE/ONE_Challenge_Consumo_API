# Conversor de Moneda - Challenge ONE

## Descripción
Esta es una aplicación de consola en Java que realiza conversiones de moneda en tiempo real utilizando una API externa. El proyecto fue desarrollado como parte del Challenge de Java del programa Oracle Next Education (ONE). Permite a los usuarios elegir entre varias opciones de conversión predefinidas y ver el resultado calculado con tasas de cambio actualizadas.

## Características
- Interfaz de consola interactiva con menú de opciones.
- Conexión a la API `ExchangeRate-API` para obtener tasas de cambio en tiempo real.
- Soporte para múltiples monedas: Dólar (USD), Peso Argentino (ARS), Real Brasileño (BRL), Peso Colombiano (COP).
- Conversión precisa basada en datos actuales.
- Manejo de errores para entradas inválidas y problemas de conexión.

## Cómo usar la aplicación
1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en tu IDE de preferencia (IntelliJ, Eclipse, VS Code).
3. Asegúrate de tener configurada tu API Key en la clase `ExchangeRateService.java`.
4. Ejecuta la clase `Main.java`.
5. Selecciona una opción numérica del menú para elegir la conversión deseada.
6. Ingresa la cantidad de dinero que deseas convertir.
7. Visualiza el resultado en la consola.
8. Selecciona la opción 7 para salir.

## Requisitos y Configuración
- **Java 17** o superior.
- **Maven** para la gestión de dependencias.
- Una **API Key** válida de [ExchangeRate-API](https://www.exchangerate-api.com/).

## Tecnologías utilizadas
- **Java**: Lenguaje principal de la aplicación.
- **Maven**: Gestión del proyecto y dependencias.
- **GSON**: Librería de Google para el parseo de respuestas JSON.
- **Java HTTP Client**: Para realizar las peticiones a la API.

## Estructura del proyecto
- `Main.java`: Punto de entrada de la aplicación, contiene el menú y la lógica de interacción con el usuario.
- `ExchangeRateService.java`: Clase encargada de realizar la conexión HTTP y obtener los datos de la API.
- `ExchangeRateResponse.java`: Record utilizado para modelar y mapear la respuesta JSON de la API.
- `pom.xml`: Archivo de configuración de Maven y dependencias.

---
Desarrollado como parte del challenge de Back End de Oracle Next Education (ONE).
