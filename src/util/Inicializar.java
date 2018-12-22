package util;

import java.util.HashMap;

public class Inicializar {
	
	public static HashMap<Integer, String> inicializarDiaDaSemana() {
		HashMap<Integer, String> diaDaSemana = new HashMap<Integer, String>();
		
		diaDaSemana.put(1, "Domingo");
		diaDaSemana.put(2, "Segunda");
		diaDaSemana.put(3, "Terca");
		diaDaSemana.put(4, "Quarta");
		diaDaSemana.put(5, "Quinta");
		diaDaSemana.put(6, "Sexta");
		diaDaSemana.put(7, "Sabado");
		
		return diaDaSemana;
	}
	public static HashMap<String, Integer[]> inicializarAtributoIndices() {
		HashMap<String, Integer[]> atributoIndices = new HashMap<String, Integer[]>();
		
		Integer[] indices = {0,6};
		atributoIndices.put("stn", indices);
		
		indices = new Integer[2];
		indices[0] = 7;
		indices[1] = 12;
		atributoIndices.put("wban", indices);
		
		indices = new Integer[2];
		indices[0] = 24;
		indices[1] = 30;
		atributoIndices.put("temp", indices);
		
		indices = new Integer[2];
		indices[0] = 31;
		indices[1] = 33;
		atributoIndices.put("countTemp", indices);
		
		indices = new Integer[2];
		indices[0] = 35;
		indices[1] = 41;
		atributoIndices.put("dewp", indices);
		
		indices = new Integer[2];
		indices[0] = 42;
		indices[1] = 44;
		atributoIndices.put("countDewp", indices);
		
		indices = new Integer[2];
		indices[0] = 46;
		indices[1] = 52;
		atributoIndices.put("slp", indices);
		
		indices = new Integer[2];
		indices[0] = 53;
		indices[1] = 55;
		atributoIndices.put("countSlp", indices);
		
		indices = new Integer[2];
		indices[0] = 57;
		indices[1] = 63;
		atributoIndices.put("stp", indices);
		
		indices = new Integer[2];
		indices[0] = 64;
		indices[1] = 66;
		atributoIndices.put("countStp", indices);
		
		indices = new Integer[2];
		indices[0] = 68;
		indices[1] = 73;
		atributoIndices.put("visib", indices);
		
		indices = new Integer[2];
		indices[0] = 74;
		indices[1] = 76;
		atributoIndices.put("countVisib", indices);
		
		indices = new Integer[2];
		indices[0] = 78;
		indices[1] = 83;
		atributoIndices.put("wdsp", indices);
		
		indices = new Integer[2];
		indices[0] = 84;
		indices[1] = 86;
		atributoIndices.put("countWdsp", indices);
		
		indices = new Integer[2];
		indices[0] = 88;
		indices[1] = 93;
		atributoIndices.put("maxSpd", indices);
		
		indices = new Integer[2];
		indices[0] = 95;
		indices[1] = 100;
		atributoIndices.put("maxGust", indices);
		
		indices = new Integer[2];
		indices[0] = 102;
		indices[1] = 108;
		atributoIndices.put("maxTemp", indices);
		
		indices = new Integer[2];
		indices[0] = 108;
		indices[1] = 109;
		atributoIndices.put("flagMaxTemp", indices);
		
		indices = new Integer[2];
		indices[0] = 110;
		indices[1] = 116;
		atributoIndices.put("minTemp", indices);
		
		indices = new Integer[2];
		indices[0] = 116;
		indices[1] = 117;
		atributoIndices.put("flagMinTemp", indices);
		
		indices = new Integer[2];
		indices[0] = 118;
		indices[1] = 123;
		atributoIndices.put("prcp", indices);
		
		indices = new Integer[2];
		indices[0] = 123;
		indices[1] = 124;
		atributoIndices.put("flagPrcp", indices);
		
		indices = new Integer[2];
		indices[0] = 125;
		indices[1] = 130;
		atributoIndices.put("sndp", indices);
		
		indices = new Integer[2];
		indices[0] = 132;
		indices[1] = 133;
		atributoIndices.put("fog", indices);
		
		indices = new Integer[2];
		indices[0] = 133;
		indices[1] = 134;
		atributoIndices.put("rain", indices);
		
		indices = new Integer[2];
		indices[0] = 134;
		indices[1] = 135;
		atributoIndices.put("snow", indices);
		
		indices = new Integer[2];
		indices[0] = 135;
		indices[1] = 136;
		atributoIndices.put("hail", indices);
		
		indices = new Integer[2];
		indices[0] = 136;
		indices[1] = 137;
		atributoIndices.put("thunder", indices);
		
		indices = new Integer[2];
		indices[0] = 137;
		indices[1] = 138;
		atributoIndices.put("tornado", indices);
		
		return atributoIndices;
	}
	public static HashMap<String, Boolean> inicializarAtributoEhNumerico() {
		HashMap<String, Boolean> atributoEhNumerico = new HashMap<String, Boolean>();
		
		atributoEhNumerico.put("stn", true);
		atributoEhNumerico.put("wban", true);
		atributoEhNumerico.put("temp", true);
		atributoEhNumerico.put("countTemp", true);
		atributoEhNumerico.put("dewp", true);
		atributoEhNumerico.put("countDewp", true);
		atributoEhNumerico.put("slp", true);
		atributoEhNumerico.put("countSlp", true);
		atributoEhNumerico.put("stp", true);
		atributoEhNumerico.put("countStp", true);
		atributoEhNumerico.put("visib", true);
		atributoEhNumerico.put("countVisib", true);
		atributoEhNumerico.put("wdsp", true);
		atributoEhNumerico.put("countWdsp", true);
		atributoEhNumerico.put("maxSpd", true);
		atributoEhNumerico.put("maxGust", true);
		atributoEhNumerico.put("maxTemp", true);
		atributoEhNumerico.put("flagMaxTemp", false);
		atributoEhNumerico.put("minTemp", true);
		atributoEhNumerico.put("flagMinTemp", false);
		atributoEhNumerico.put("prcp", true);
		atributoEhNumerico.put("flagPrcp", false);
		atributoEhNumerico.put("sndp", true);
		
		atributoEhNumerico.put("fog", false);
		atributoEhNumerico.put("rain", false);
		atributoEhNumerico.put("snow", false);
		atributoEhNumerico.put("hail", false);
		atributoEhNumerico.put("thunder", false);
		atributoEhNumerico.put("tornado", false);
		
		return atributoEhNumerico;
	}
}
