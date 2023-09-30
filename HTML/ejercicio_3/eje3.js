const colors = ["yellow","red","blue","green","blueviolet","pink","maroon","lime","aqua","orange","olive",
"grey","gold","silver","purple","navy","beige","darkslateblue","deeppink","fuchsia","salmon"];

let timer; // almaceno el setinterval para controlar si está activo
let cellsNumber; //nº de celdas a dibujar
let minRows; //nº de filas
let minCols; //nº de columnas
let cellsOrdered = []; // Guarda las celdas a medida que se dibujan en el div
let cellsUnOrdered = []; // Guarda las celdas de cellsOrdered pero desordenadas
let colorsArray = []; // Guarda los colores que se van a usar, si hay más de los que hay en colors tendrá que duplicarlos.
let lastCell; // Guarda la última celda seleccionada
let matches; // nº de parejas hechas
let attempts; // nº de intentos
let gameTime; // tiempo de juego (boton generar hasta hacer ultima pareja)


function getCells() { 
    cellsNumber = 0; 
    minRows = 0; 
    minCols = 0; 
    cellsOrdered = []
    cellsUnOrdered =[]
    colorsArray =[]
    lastCell = null; 
    matches = 0; 
    attempts = 0; 
    gameTime = 1;
   
    startCounter(); //inicializo el timer   
    document.getElementById("lblMatches").innerHTML = "";

    cellsNumber = document.getElementById("cellsNumber").value; // recupero el valor de cellsNumber del html

    // Compruebo si es par
    if (cellsNumber % 2 != 0) {
        alert("El numero ha de ser par");
        return;
    }

    // Si estoy aqui el nº es par
    //calculo la raiz cuadrada que me dará el minimo de filas y de columnas
    minRows = Math.floor(Math.sqrt(cellsNumber));
    minCols = minRows;

    // Si necesito más celdas que el minimo añado una fila
    if (cellsNumber > (minRows * minCols)) {
        minRows++;
    }

    // Si no es suficiente añado una columna
    if (cellsNumber > (minRows * minCols)) {
        minCols++;
    }

    paintCells();
    
}

function paintCells() {
   // borro lo que este dentro del div
   document.getElementById("grid").innerHTML = "";

    // capturo los valores
    let rows = minRows;
    let cols = minCols;

     // tengo que eliminar el pixel del borde de cada uno de los div interiores
    let cellHeight = (400 - (minRows * 2)) / minRows;
    let cellWidth = (400 - (minCols * 2)) / minCols;
                    
    // Ahora voy dibujando cada una de las celdas
    for (i = 1; i <= cellsNumber; i++) {

        let newCell = document.createElement('div');

         // añado floatleft paa que se coloquen a la izquierda sin abrir nueva linea          
        newCell.classList.add("floatleft");
        // establezco sus atributos y los añado al grid
        newCell.style.width = cellWidth + "px";
        newCell.style.height = cellHeight + "px";
        newCell.style.backgroundColor= "white"; // lo pinto en blanco por defecto...        
        newCell.gameColor = "white"; // tengo que ponerle el color como un atributo nuevo. El JS es la leche....
         // añado un evento para gestionar el click en la celda
         newCell.onclick = function(){
            onClickCell(newCell);
         };
        
        // añado la nueva celda al grid.
        document.getElementById("grid").appendChild(newCell);
       
        // la añado al array de celdas...
        cellsOrdered.push(newCell);                 
    }
    // Tengo todas las celdas ordenadas, ahora les tengo que asignar colores aleatoriamente
    assignColors();
}

function assignColors() {

    let colorSelected = 0;
    let colorsArray = colors;

    // Compruebo si tengo colores suficientes, si no es asi los duplico
    if (cellsNumber > colors.length) {
        colorsArray = colorsArray.concat(colors);
    }

    // Asigno esos colores a las celdas aleatoriamente.
    do
    {
        // Hago 2 de cada vez para tener las parejas
        let rnd = randomNumber(0,cellsOrdered.length-1);
        let first = cellsOrdered[rnd]
        first.gameColor = colorsArray[colorSelected];       
        cellsUnOrdered.push(first);
        cellsOrdered.splice(rnd,1);

        rnd = randomNumber(0,cellsOrdered.length-1);
        let second = cellsOrdered[rnd]
        second.gameColor = colorsArray[colorSelected];        
        cellsUnOrdered.push(second);
        cellsOrdered.splice(rnd,1);
        colorSelected++;

    }  while (cellsOrdered.length > 0);
}

function onClickCell(currentCell) {

    // Si solo tengo seleccionada una celda la guardo y me marcho
    if (lastCell == null) {
        lastCell = currentCell;
        currentCell.style.backgroundColor = currentCell.gameColor;
        return;
        
    }   
    // Tengo seleccionadas 2. Comparo sus colores
    if (lastCell.gameColor == currentCell.gameColor)
    {
        // acerte
        currentCell.style.backgroundColor = currentCell.gameColor;
        lastCell.onclick = null;
        currentCell.onclick = null;
        matches++;
        attempts++;
        // Compruebo si ya he hecho todas las parejas
        if (matches == cellsUnOrdered.length/2)
        {
            // Game over
            document.getElementById("lblMatches").innerHTML = "Has hecho " + matches + " parejas en " + gameTime + " segundos con " + attempts + " intentos.";
            return;
        }
        // Quedan parejas por hacer...borro la celda y sigo....
        lastCell = null;

    }
    else
    {
        // falle
        // Las escondo otra vez
        currentCell.style.backgroundColor = currentCell.gameColor;
        setTimeout(() => {
            currentCell.style.backgroundColor = "white";
            lastCell.style.backgroundColor = "white";  
            // no puedo ponerlo al final del if.....
            lastCell=null; // si no lo meto aqui lo ejecuta ANTES de que se dispare el settimeout con lo que cuando llega el timeout me da un nullpointer....    
            }, "500");  
        // sumo un intento más
        attempts++;
    } 
}    


function startCounter() {

    let lblTimer = document.getElementById("lblTimer"); //obtengo una referencia a la label en el html
    if (timer != null) clearInterval(timer); // si ya existe un timer lo elimino
    timer = setInterval(getGameTime, 1000); // creo el timer
    gameTime = 1; // inicializo un contador que aumentara con cada tick del timer para los segundos.
    function getGameTime() {
        lblTimer.innerHTML = gameTime.toString(); //cargo el contador en el contenido de la label
        gameTime++;
    }

}

function randomNumber(min, max) {
    return Math.round(Math.random() * (max - min) + min);
}
