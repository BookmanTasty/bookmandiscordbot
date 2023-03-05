package org.leyvadev.bookmandiscordbot.utils;

import java.math.BigDecimal;
import java.util.Random;


public class LolMessageService {

    private static final String[] BAD_MESSAGES = {
            "Tuve más miedo en la selección de campeones",
            "¿Te dormiste durante el juego?",
            "Creo que olvidaste que estabas jugando",
            "¿Eso fue un intento de jugar?",
            "Hay más futuro en ser un minion",
            "Te iría mejor en la jungla, porque en línea no tienes idea",
            "Espero que esto no sea lo mejor que puedas hacer",
            "¿No es hora de que practiques en el tutorial?",
            "Incluso el barón juega mejor que tú",
            "Deberías considerar cambiar de juego",
            "¿Te sientes bien? Porque juegas como si te hubieran echado un mal de ojo",
            "Si la habilidad fuera un rango, estarías en Bronce 7",
            "Eres más predecible que un Garen con Phantom Dancer",
            "Tus jugadas son más inútiles que un Teemo sin su seta",
            "Tu habilidad en el juego es tan baja que deberían reportarte por trolling",
            "Con tu juego, el equipo contrario pensará que jugamos en modo fácil",
            "Tu rendimiento es tan malo que incluso los minions piensan que eres uno de ellos",
            "¿Tu intención es hacer que perdamos o simplemente estás practicando para ser feed?"
    };

    private static final String[] MEDIUM_MESSAGES = {
            "No estuvo mal, pero necesitas mejorar",
            "Tuviste algunos buenos momentos, pero aún falta",
            "No está mal para un aficionado",
            "Sigue practicando, llegarás lejos",
            "Aún tienes mucho que aprender, pero vas por buen camino",
            "No eres el mejor, pero tampoco el peor",
            "Podrías ser mejor, pero no estuvo mal",
            "No está mal, pero necesitas trabajar en algunas cosas",
            "Hay potencial, pero necesitas seguir practicando",
            "Podrías sorprender a todos si mejoras un poco más",
            "No estuvo mal, pero podrías hacerlo mejor",
            "Pudiste haber jugado mejor, pero al menos no dimos vergüenza",
            "Tu habilidad es como una caja de sorpresas: nunca sabemos qué esperar",
            "Eres como un Support que se olvida de poner wards",
            "Tus jugadas no son un desastre total, pero no esperes un premio de la multitud",
            "Tienes algunas habilidades, pero falta afinarlas un poco",
            "No eres exactamente el mejor, pero al menos no eres el peor",
            "No te preocupes, solo necesitas practicar un poco más",
            "Podrías ser mejor, pero al menos no eres un Yasuo en nuestro equipo",
            "Al menos no eres uno de esos jugadores que se la pasan flameando"
    };

    private static final String[] GOOD_MESSAGES = {
            "Muy buen juego, te estás convirtiendo en un profesional",
            "Impresionante, continúa así",
            "Excelente trabajo, sigue mejorando",
            "Eres bueno, pero aún puedes ser mejor",
            "Muy buen desempeño, sigue así",
            "Increíble, eres muy bueno",
            "Cada vez juegas mejor",
            "Estás mejorando rápidamente",
            "No hay límites para ti",
            "Eres una leyenda en ciernes",
            "Muy buen juego, estás mejorando",
            "Impresionante, continúa así",
            "Eres un verdadero MVP",
            "Has jugado bien, pero todavía hay margen para mejorar",
            "¡Estoy impresionado por tus habilidades!",
            "Tienes un gran potencial, sigue así",
            "Sigue jugando así y podrás subir de rango fácilmente",
            "Eres como una serpiente: sigues mejorando con cada nivel",
            "Sigue así y el equipo contrario temerá enfrentarse a ti",
            "Tus habilidades son tan buenas que pareces estar smurfeando",

    };

    private static final String[] AMAZING_MESSAGES = {
            "Simplemente espectacular, no hay nadie como tú",
            "Increíble, no sé cómo lo haces",
            "Eres una máquina, nunca te detengas",
            "El mejor jugador que he visto en mi vida",
            "Es un placer jugar contigo",
            "Sin palabras, simplemente impresionante",
            "Eres una leyenda en vida",
            "Cada vez que juegas, haces historia",
            "Nadie puede igualar tu habilidad",
            "Eres el mejor de todos los tiempos",
            "Eres un verdadero dios del juego",
            "Simplemente impresionante, no tengo palabras",
            "Deberías considerar unirte a un equipo profesional",
            "Eres un verdadero prodigio del juego",
            "Estás en un nivel que solo unos pocos jugadores pueden alcanzar",
            "Deberías dar lecciones de juego, tienes mucho que enseñar",
            "No eres solo un jugador, eres una leyenda viva",
            "Eres tan bueno que deberías empezar a jugar con los pies"
    };

    private static final Random random = new Random();

    public static String getMessageForRating(BigDecimal rating) {
        if (rating == null) {
            return "Sin clasificación, ¿quizás deberías jugar primero?";
        }
        int index = rating.setScale(0, BigDecimal.ROUND_HALF_UP).intValueExact() * 10;
        String[] messages;
        if (index < 10) {
            messages = BAD_MESSAGES;
        } else if (index < 30) {
            messages = MEDIUM_MESSAGES;
        } else if (index < 50) {
            messages = GOOD_MESSAGES;
        } else {
            messages = AMAZING_MESSAGES;
        }
        int messageIndex = random.nextInt(messages.length);
        return messages[messageIndex];
    }
}