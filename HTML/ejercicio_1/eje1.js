
        function getDivs() {
            // borro lo que este dentro del div
            document.getElementById("grid").innerHTML="";

            // capturo los valores
            let rows = document.getElementById("rownum").value;
            let cols = document.getElementById("colnum").value;

            // tengo que eliminar el pixel del borde de cada uno de los div interiores
            let cellHeight = (400 - (rows * 2)) / rows;
            let cellWidth = (400 - (cols * 2)) / cols;
                          
            // El total de divs serán columnas*filas, las dibujo
            for(i = 1; i <= rows*cols; i++) {
                let newCell = document.createElement('div');   
                // añado floatleft paa que se coloquen a la izquierda sin abrir nueva linea                         
                newCell.classList.add("floatleft");
                // establezco sus atributos y los añado al grid
                newCell.style="width:" + cellWidth + "px; height:" + cellHeight + "px";
                // añado la nueva celda al grid.
                document.getElementById("grid").appendChild(newCell); 
            }

        }
       
