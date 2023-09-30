
let timer; // alamceno el setinterval para controlar si está activo
let cellsNumber; //nº de celdas a dibujar
let minRows; //nº de filas
let minCols; //nº de columnas

// obtiene el nº de celdas a dibujar y llama a getDivs para pintarlas
function getCells() {
    // Init variables
    cellsNumber = 0; 
    minRows = 0; 
    minCols = 0; 

    startCounter(); //inicializo el timer

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

    // tengo que eliminar el pixel del borde de cada uno de los div interiores
    let cellHeight = (400 - (minRows * 2)) / minRows;
    let cellWidth = (400 - (minCols * 2)) / minCols;

    // El total de divs serán columnas*filas, las dibujo
    for (i = 1; i <= cellsNumber; i++) {

        let newCell = document.createElement('div');

         // añado floatleft paa que se coloquen a la izquierda sin abrir nueva linea          
        newCell.classList.add("floatleft");
        // establezco sus atributos y los añado al grid
        newCell.style.width = cellWidth;
        newCell.style.height = cellHeight;
       
        // añado la nueva celda al grid.
        document.getElementById("grid").appendChild(newCell);

    }
}

function startCounter() {

    let lblTimer = document.getElementById("lblTimer"); //obtengo una referencia a la label en el html
    if (timer != null) clearInterval(timer); // si ya existe un timer lo elimino
    timer = setInterval(getCounter, 1000); // creo el timer
    let counter = 1; // inicializo un contador que aumentara con cada tick del timer para los segundos.
    function getCounter() {
        lblTimer.innerHTML = counter.toString(); //cargo el contador en el contenido de la label
        counter++;
    }

}





