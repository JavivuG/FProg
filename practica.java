import java.util.Scanner;
import java.io.*;
import java.lang.Math;

// Trabajo realizado por:
// Javier Garcia Gonzalez
// Javier Esgueva Sto. Domingo
// Grupo Z9

public class practica {
	// Funcion devuelve la cantidad de palabras de mas de 6 letras que hay en el archivo
	public static int numeroPalabrasBases(int tamanoBase) { 
		try {
			String line;
			
			int cont=0;
			Scanner file=new Scanner(new File("diccionario.txt")); // Declaro el scanner leer el archivo
			while(file.hasNextLine()) { // Mientras que el archivo tenga una siguiente linea hago lo siguiente
				line=file.nextLine(); // Recojo la linea 
				
				if(line.length()>=tamanoBase) {
					cont++; // Contador que enumera la cantidad de palabras mayores o iguales de 6 letras en el archivo
				}
			}
			file.close(); // Cierro el scanner
			return cont; // Devuelve el contador de palabras de 6 o mas letras
		}
		catch (FileNotFoundException e){

			return -1;
		}
	}
	
	// Funcion que elimina un caracter de un String de una posicion indicada
	public static String eliminaCaracter(int posicion, String palabra) { 
		String resultado="";
		for(int i=0; i<palabra.length(); i++) { // Elimina el caracter de la posicion indicada
			if (posicion!=i) {
				resultado+=palabra.charAt(i);	
			}
		}
		palabra=resultado;
		
		return palabra;
	}
	
	// Funcion que desordena un String
	public static String desordenar(String palabra) { 
		String desordenado="";
		int azar;
		boolean[] ocupado=new boolean[palabra.length()]; // Creo un array de boolean para saber si esta ocupado la posicion azar

		for(int i=0; i<palabra.length(); i++) { // Recorro la palabra
			azar=(int)(Math.random()*palabra.length()); // Genero un numero al azar entre 0 y 6 sin incluir
			
			while(ocupado[azar]==true) { // Si la posicion esta ocupada vuelvo a generar un numero aleatorio
				azar=(int)(Math.random()*palabra.length());
			}
			
			desordenado+=palabra.charAt(azar); // Sumo a desordenado el caracter de la palabra en la posicion indicada por el azar
			ocupado[azar]=true; // Cambio el valor de la posicion azar a ocupado
		}
		
		return desordenado; // devuelvo la palabra desordenada
	}
	
	// Funcion que devuelve la linea elegida de entre las palabras de mas de 6 letras
	public static int lineaPalabraBase(int cont) { 
			int linea;
			
			linea=(int)(Math.random()*(cont+1));
	
			return linea; // devuelvo la linea indicada para la palabra base
	}
	
	// Funcion que devuelve la palabra seleccionada
	public static String palabraBase(int linea) { 
		try {
			String palabra=""; // declaro e inicializo el string que uso para identificar la palabra base
			Scanner file=new Scanner(new File("diccionario.txt")); // Creo la clase scanner para leer el fichero
			
			for(int i=0; i<linea; i++) { // Recorro hasta la linea que habia escogido aleatoriamente
				palabra=file.nextLine(); // Leo hasta la linea que he indicado (que es aleatoria)
			}
			
			file.close(); // Cierro el scanner
			
			return palabra; // Devuelvo la palabra base elegida
			
		}
		catch (FileNotFoundException e){
			
			System.out.println("No se encontro el fichero");
			
			return null;
		}
		
	}
	
	// Funcion que devuelve la cantidad de palabras hijas que hay en el fichero
	public static int cantidadHijas(String palabra, int tamano) { 
		try {
			Scanner file=new Scanner(new File("diccionario.txt"));
			String line="";
			int i=0;
			
			while(file.hasNextLine()) { // Recorro todo el fichero
				line=file.nextLine(); 
				if (line.length()<tamano) { // Si la longitud de la palabra es menor que 6 entonces compruebo que es palabra hija
					if (esPalabraHija(palabra,line)) {
						i++; // Si es palabra hija entonces anado uno al contador
					}
				}
			}
			return i;
		}
		catch (FileNotFoundException e){
			
			System.out.println("No se encontro el fichero");
			
			return -1;
		}
		
}
	
	// Funcion que devuelve todas las palabras hijas que hay
	public static String[] palabraHija(String palabra, int tamano) { 
		try {
			Scanner file=new Scanner(new File("diccionario.txt"));
			String line="";
			String [] palabraHija=new String[cantidadHijas(palabra, tamano)]; // Creo un String donde guardare las palabras hijas
			int i=0;
			
			while(file.hasNextLine()) { // Recorro el fichero entero
				line=file.nextLine(); 
				
				if (line.length()<tamano) {
					if (esPalabraHija(palabra,line)) { // Compruebo que sea palabra hija
						palabraHija[i]=line; // Si lo es entonces la pongo en la posicion i del array de Strings
						i++;
					}
				}	
			}
			
			return palabraHija;
		}
		catch (FileNotFoundException e){
			
			System.out.println("No se encontro el fichero");
			
			return null;
		}
	}

	// Funcion que comprueba si una determinada palabra es hija de otra
	public static boolean esPalabraHija(String palabra, String line) { 
		// Para que una palabra sea hija de otra (base) la base debe tener todas las letras de la hija
		boolean esHija=false; // Declaro el boolean
		
		// Line es la palabra hija
		for (int i=0; i<line.length(); i++) { // Recorro el String line
			char c=line.charAt(i); // Asigno el caracter de la posicion i
			if (palabra.indexOf(c) != -1) { // Comprueba si esta en la palabra el caracter c
				esHija=true;
			}
			else {
				esHija=false; // Si el caracter de la palabra hija no esta en la palabra base entonces pongo el boolean a false y lo devuelvo
				return esHija;
			}
			palabra=eliminaCaracter(palabra.indexOf(c), palabra); // A la palabra se le elimina el caracter c para evitar que se repita un mismo caracter
		}
		return esHija; // Devuelvo el valor de esHija ( true si es palabra hija y false si no lo es)
		
	}
	
	// Funcion que devuelve un boolean con las palabras hijas previstas elegidas
	public static boolean[] eleccionPalabrasHijasPrevistas(String[] palabrasHijas, int eleccionPalabrasHijas) { 
		boolean[] ocupado=new boolean[palabrasHijas.length+1]; // Declaro un boolean que tenga la dimension de palabras hijas para saber si se ha repetido alguno
		int azar;
		
		for (int i=0; i<eleccionPalabrasHijas; i++) { // Repito la funcion hasta que tenga las 5 palabras hijas elegidas
			do {
				azar=(int)(Math.random()*palabrasHijas.length); // Genero un numero al azar de 0 a la cantidad de palabras hijas
			}while(ocupado[azar]==true); // Si esa posicion ya habia sido elegida repito el azar
			ocupado[azar]=true; 
		}
		
		return ocupado; // Devuelvo las palabras Hijas previstas elegidas
	}
	
	// Funcion que me devuelve las palabras hijas previstas
	public static String[] palabrasHijasPrevistas(String[] palabrasHijas, boolean[] ocupado) { 
		// El array de palabrasHijas almacena todas las palabras Hijas que hay
		String[] palabrasHijasPrevistas=new String[5]; // String que almacenara las 5 palabras hijas previstas
		int cont=0;
		
		for (int i=0; i<palabrasHijas.length; i++) { 
			if (ocupado[i]==true) { // Si el boolean esta en true eso me indica que la posicion que corresponde a esa palabra habia sido elegida
				palabrasHijasPrevistas[cont]=palabrasHijas[i]; 
				cont++;
			}
		}
		return palabrasHijasPrevistas; // Devuelve las palabras hijas previstas
	}
	
	// Funcion que devuelve las palabras hijas no previstas
	public static String[] palabrasHijasNoPrevistas(String[] palabrasHijas, boolean[] ocupado, String[] palabrasHijasPrevistas) { 
		String[] palabrasHijasNoPrevistas=new String[palabrasHijas.length-palabrasHijasPrevistas.length]; // Almacena todas las palabras hijas que no son previstas
		int cont=0;
		
		for(int i=0; i<palabrasHijas.length; i++) { // Recorro todas las palabras hijas
			if(ocupado[i]==false) { // Compruebo que esa posicion no habia sido elegida
				palabrasHijasNoPrevistas[cont]=palabrasHijas[i]; // En ese caso almacena la palabra hija correspondiente a esa posicion
				cont++;
			}
		}
		return palabrasHijasNoPrevistas; // Devuelvo el array de palabras hijas no previstas
	}

	// Funcion que comprueba que todas las letras de la entrada esten en la palabra base
	public static boolean esValido(String entrada, String palabra) { 
		boolean esValido=false;
		for(int i=0; i<entrada.length(); i++) { // Comprueba que las letras de la palabra de la entrada esten en la palabra
			char c=entrada.charAt(i);
			if (palabra.indexOf(c)!=-1) {
				esValido=true;
			}
			else { // Si alguna de las letras de la entrada no estan en la palabra base no es valida la entrada del usuario
				esValido=false;
				return esValido;
			}
			palabra=eliminaCaracter(palabra.indexOf(c), palabra); // Actualizo la palabra quitandole el caracter ya visto
		}
		return esValido; // Devuelvo si es valida o no
	}
	
	// Funcion que devuelve un boolean si coincide o no con la palabra Hija prevista
	public static boolean coincideHijasPrevistas(String[] palabrasHijasPrevistas, String entrada) { 
		boolean coincide=false;
		
		for (int i=0; i<palabrasHijasPrevistas.length; i++) { // Recorro las palabras hijas previstas
			if(entrada.equals(palabrasHijasPrevistas[i])) { // Compruebo que la entrada del usuario sea igual a alguna de las palabras hijas previstas
				coincide=true; // Si lo es pongo el boolean en true y lo devuelvo
				return coincide;
			}
		}
		return coincide; // Devuelvo el boolean
	}
	
	// Funcion que devuelve true en el caso de que la entrada del usuario coincida con una palabra Hija no prevista
	public static boolean coincideHijasNoPrevistas(String[] palabrasHijasNoPrevistas, String entrada) { 
		boolean coincide=false; // Declaro un boolean para saber si la entrada coincide
		
		for(int i=0; i<palabrasHijasNoPrevistas.length; i++) { // Recorro el array de las palabras hijas no previstas
			if(entrada.equals(palabrasHijasNoPrevistas[i])) { // Si la entrada del usuario es igual a alguna de las palabras hijas no previstas
				coincide=true; // Devuelvo el boolean puesto en true para decir que si coincide
				return coincide;
			}
		}
		return coincide; // Devuelvo el boolean
	}
	
	// Funcion que devuelve la posicion en el que el string es igual a la entrada del usuario
	public static int posicionHijasPrevistas(String[] palabrasHijasPrevistas, String entradaUsuario) { 
		int posicion=0;
		
		for(int i=0; i<palabrasHijasPrevistas.length; i++) {
			if (entradaUsuario.equals(palabrasHijasPrevistas[i])) {
				posicion=i;
			}
		}
		return posicion; // Devuelvo la posicion en la que la palabra hija prevista es igual a la entrada del usuario
	}
	
	// Funcion que devuelve la posicion en el string que es igual a la entrada del usuario
	public static int posicionHijasNoPrevistas(String[] palabrasHijasNoPrevistas, String entradaUsuario) { 
		int posicion=0;
		
		for(int i=0; i<palabrasHijasNoPrevistas.length; i++) {
			if(entradaUsuario.equals(palabrasHijasNoPrevistas[i])) {
				posicion=i;
			}
		}
		return posicion; // Devuelvo la posicion en la que la palabra hija no prevista es igual a la entrada del usuario
	}

	// Funcion que comprueba todas las variantes de respuestas para continuar con el programa
	public static boolean seContinua(String entrada) { 
		boolean continua=false;
		if (entrada.equals("Si") || entrada.equals("SI") || entrada.equals("sI") || entrada.equals("si")) { // Si se escribe de cualquier combinacion si entonces se continua el programa
			continua=true;
			return continua;
		}

		return continua; // Devuelve el boolean
	}
	
	// Funcion que lee el fichero que guarda la puntuacion
	public static int leePuntuacion() { 
		try {
			int puntuacion=0;
			Scanner file=new Scanner (new File("puntuacion.txt")); // Declaro la clase Scanner para leer la puntuacion guardada en el fichero
			while(file.hasNextInt()) { 
				puntuacion=file.nextInt(); // Asigno la puntuacion del fichero
			}
			return puntuacion; // Devuelvo la puntuacion
		}
		catch(FileNotFoundException e) {
			return 0;
		}
	}
	
	// Procedimiento que recibe la puntuacion de la partida actual y actualiza el fichero con la nueva puntuacion
	public static void escribePuntuacion(int puntuacion) { 
		PrintWriter escribir;
		try {
			int puntuacionTotal;
			escribir=new PrintWriter("puntuacion.txt"); // Creo un PrinWrite para escribir en el fichero que guarda la puntuacion
			puntuacionTotal=leePuntuacion(); // Le asigno a puntuacionTotal el valor guardado en el fichero
			puntuacionTotal+=puntuacion; // Suma a la puntuacion total la puntuacion de la partida
			
			escribir.println(puntuacionTotal);
			escribir.close();
		}
		catch(IOException e){
			System.out.println("Error de escritura");
		}
	}
	
	public static void main(String[] args) {
		// Constantes
		final int tamanoBase=6; // Constante que indica el tamano de la palabra base
		final int eleccionPalabrasHijas=5; // Constante que me indica la cantidad de palabras hijas previstas se van a elegir
		
		// Variables
		int linea; // linea hasta la cual las palabras tienen 6 o mas letras
		int finalizacion=0; // En cuanto se adivinan todas las palabras el juego finaliza
		int puntuacionPartida=leePuntuacion(); // Muestra la puntuacion total de la partida
		int puntuacion;
		
		String desordenada; // Palabra base desordenada
		String palabraBase; // Palabra base elegida
		String entradaUsuario; // Entrada del usuario para adivinar las palabras
		String continuacionPartida; // entrada del usuario que me indica si desea continuar la partida o no
		
		boolean palabraBaseEncontrada; // boolean que indica si la palabra base se ha encontrado o no
		Scanner entrada=new Scanner(System.in); // Declaro el Scanner
		
		do { // Empieza la partida
			linea=lineaPalabraBase(numeroPalabrasBases(tamanoBase)); // Eligo la linea de la palabra base
			palabraBase=palabraBase(linea); // Elijo la palabra base
			desordenada=desordenar(palabraBase); // Asigno al string la palabra desordenada de la palabra base
			
			// Palabras hijas
			String[] palabrasHijas=new String[cantidadHijas(palabraBase, tamanoBase)]; // Creo un String temporal para poder guardar las palabras hijas
			palabrasHijas=palabraHija(palabraBase, tamanoBase); // Busco las palabras hijas de la palabra base con tamano menor que tamanoBase
			int cantidadHijas=cantidadHijas(palabraBase, tamanoBase); // Cantidad de palabras hijas en el fichero
			
			// Palabras Hijas Previstas
			String[] palabrasHijasPrevistas=new String[eleccionPalabrasHijas]; // Creo un string para guardar las palabras hijas previstas
			boolean[] ocupado=new boolean[cantidadHijas]; // boolean que me indica las palabras hijas previstas
			ocupado=eleccionPalabrasHijasPrevistas(palabrasHijas, eleccionPalabrasHijas); // Guardo las posiciones que se han elegido de las palabras hijas
			palabrasHijasPrevistas=palabrasHijasPrevistas(palabrasHijas, ocupado); // Guardo las palabras hijas previstas
			boolean[] ocupadoHijasPrevistas=new boolean[palabrasHijasPrevistas.length]; // boolean que me indica si la palabra hija prevista ha sido adivinado o no
			
			// Palabras Hijas No Previstas
			String[] palabrasHijasNoPrevistas=new String[cantidadHijas(palabraBase,tamanoBase)-(palabrasHijasPrevistas.length)]; // Creo un String para guardar las palabras Hijas No previstas
			palabrasHijasNoPrevistas=palabrasHijasNoPrevistas(palabrasHijas, ocupado,palabrasHijasPrevistas);
			boolean[] ocupadoHijasNoPrevistas=new boolean[palabrasHijasNoPrevistas.length]; // boolean que me indica si la palabra hija no prevista ha sido adivinada o no
			
			// Reseteo las variables de las que depende la partida
			finalizacion=0;// Variable que me indica si se han adivinado todas las palabras o no
			puntuacion=0; // Puntuacion de la partida actual
			palabraBaseEncontrada=false;

			do { // Bucle de la misma partida, se acaba cuando se adivinan todas las hijas previstas y la palabra base
				System.out.println(desordenada); // Muestro en pantalla la palabra base desordenada
				
				int posicionHijasPrevistas, posicionHijasNoPrevistas;
				
				if(palabraBaseEncontrada==false) { // Si no se ha encontrado la palabra Base entonces muestro las lineas que ocupa
					for(int i=0; i<palabraBase.length(); i++) {
						System.out.print("_");
					}
				}
				else {
					System.out.print(palabraBase); // Si se ha encontrado la escribo
				}
				System.out.println();
				
				for(int i=0; i<ocupadoHijasPrevistas.length; i++) { // Muestro las lineas para las palabras hijas previstas
					if(ocupadoHijasPrevistas[i]==false) { // Compruebo que no haya sido adivinada
						for(int j=0; j<palabrasHijasPrevistas[i].length(); j++) {
							System.out.print("_");
						}
					}
					else {
						System.out.print(palabrasHijasPrevistas[i]); // Si se ha adivinado entonces la escribo
					}
					System.out.println();
					
				}

				entradaUsuario=entrada.nextLine(); // Leo la entrada del usuario
				System.out.println();
				
				if (esValido(entradaUsuario, palabraBase)){ // Si todas las letras de la entrada estan en la palabra base
					if(entradaUsuario.equals(palabraBase)){ // Compruebo si la entrada coincide con la palabra base
						if(palabraBaseEncontrada==false) { // Si no habia sido elegida entonces sumo puntuacion
							for (int i=0; i<entradaUsuario.length(); i++) {
								puntuacion++;
							}
							finalizacion++; 
							palabraBaseEncontrada=true; // Pongo el boolean en true para que no se vuelva a adivinar y puntue
						}
						else {
							System.out.println("Ya habias adivinado la palabra base"); // Si la habia adivinado se lo digo
						}
					}
					else if(coincideHijasPrevistas(palabrasHijasPrevistas, entradaUsuario)){ // Si la entrada del usuario coincide con alguna palabra hija prevista
						posicionHijasPrevistas=posicionHijasPrevistas(palabrasHijasPrevistas, entradaUsuario); // Compruebo la posicion en la cual la palabra hija prevista es igual a la entrada del usuario
						
						if(ocupadoHijasPrevistas[posicionHijasPrevistas]==false) { // Si no ha sido adivinada antes entonces la entrada es valida y cuento los puntos
							ocupadoHijasPrevistas[posicionHijasPrevistas]=true; // Asigno true para saber que esa posicion ya ha sido elegida
							for (int i=0; i<entradaUsuario.length(); i++) { // Sumo tantos puntos como letras tenga la palabra
								puntuacion++; 
							}
							finalizacion++;
						}
						else { // Si ya habia sido adivinada entonces lo muestro y dicho que ya habia sido adivinada
							System.out.println("Ya habias adivinado esta palabra hija prevista");
						}
					}
					else if (coincideHijasNoPrevistas(palabrasHijasNoPrevistas, entradaUsuario)) { // Si coincide con alguna no prevista
						posicionHijasNoPrevistas=posicionHijasNoPrevistas(palabrasHijasNoPrevistas, entradaUsuario); // Guardo la posicion en la que la palabra hija coincide con la entrada del usuario
						
						if(ocupadoHijasNoPrevistas[posicionHijasNoPrevistas]==false) { // Si no ha sido elegida antes cuento la puntuacion
							puntuacion+=1;
							System.out.println("Punto extra");
							ocupadoHijasNoPrevistas[posicionHijasNoPrevistas]=true; // Le asigno true para que no vuelva a ser elegida
						}
						else { // Si ya habia sido elegida lo muestro en pantalla
							System.out.println("Ya habias adivinado esta palabra hija"); 
						}
					}
					else {
						System.out.println("No esta en el diccionario"); // Si es valida y no coincide ni con palabras base, hijas previstas y no previstas es que no esta en el diccionario
					}
				}
				else { // Si sus letras no estan en la palabra base
					System.out.println("Palabra invalida");
				}
			}while(finalizacion<palabrasHijasPrevistas.length+1); // Hasta que se adivinen las palabras hijas previstas + la palabra base

			puntuacionPartida+=puntuacion; // La puntuacion total de las partida se le suma la de esta partida
			escribePuntuacion(puntuacionPartida); // Guardo la puntuacion de todas las partidas realizadas en el fichero
			
			System.out.println("PARTIDA TERMINADA");
			System.out.println("Puntos esta partida: "+ puntuacion); // Muestro la puntuacion de la partida actual
			System.out.println("Puntos acumulados:" + puntuacionPartida); // Muestro la puntuacion total obtenida
			System.out.print("Otra partida?"); // Pregunto al usuario si se continua con la partida
			continuacionPartida=entrada.nextLine();
			
		}while(seContinua(continuacionPartida)); // Compruebo que diga si, en ese caso se continua con la partida
		
		entrada.close(); // Cierro la clase Scanner
	}
}
