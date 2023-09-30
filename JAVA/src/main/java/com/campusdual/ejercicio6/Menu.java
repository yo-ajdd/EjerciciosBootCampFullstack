package com.campusdual.ejercicio6;

//--Teniendo en cuenta el programa anterior(ejercicio4):
//--Realiza los cambios necesarios para:
//  -La dieta ya no será única si no que se guardará en una lista de dietas. Esta lista tendrá un nombre para poder ser recuperado. Será necesario cambiar el menú para gestionar esta lista. Ahora en ved de crear/reemplazar dieta lo que haremos será:
//    a- Agregar dieta: Añade una dieta a la lista. Podemos reutilizar el menú antiguo de crear/reemplazar
//    b- Mostrar detalles de dieta: Muestra los detalles de una dieta y permite modificarla
//    c- Eliminar dieta: Elimina una dieta de la lista(Siempre que no la tenga asignada un paciente)
//
//  -Se pueden dar de alta personas: Las personas tendrán los siguientes atributos: nombre, apellidos, peso, altura, edad y sexo.
//    -Las personas podrán tener asignadas una lista de dietas que se catalogarán de lunes a domingo sacadas de la lista de dietas anterior.
//    -Se agregara un apartado nuevo al menú de "Gestión de pacientes" con las siguientes funcionalidades:
//      1- Dar de alta un paciente : Agregará un paciente nuevo a la lista de pacientes
//      2- Listar y Mostrar detalles de un paciente : Mostrará el detalle de un paciente así como todas sus dietas listadas de Lunes a domingo
//      3- Asignar una dieta: Muestra la lista de dietas general y puede asignarle una a un día de la semana del paciente.
//      4- Dar de baja un paciente: Elimina los datos de un paciente(No borra sus dietas asignadas dado que pueden estar asignadas a otro paciente)

//--Teniendo en cuenta las funcionalidades anteriores modificar el programa para realizar las sigueintes acciones
//        --Los alimentos predefinidos ahora se guardarán en un fichero, habrá un menú adicional que permita:
//        a-añadir un nuevo alimento HECHO
//        b-listarlos: pudiendo modificar cualquier parámetro del mismo HECHO
//        c-eliminarlos HECHO
//        --Los pacientes ahora se guadararan en un fichero, - HECHO
//        se mantendrá el menú que permite listar pacientes pero
//        se agregará una nueva opción que permita buscarlos por nombre y apellido. HECHO
//        Las acciones de borrado de clientes los borran del fichero. HECHO
//        --Las dietas ahora se guardarán en un fichero. Se mantendrá la funcionalidad de listarlas pero se agregará una nueva opción de buscarlas por nombre.
//        --Se crearán una nueva opciones en el menú en la gestión de dietas
//        a-opción de mostrar estadisticas de uso: esta opción nos indicará en orden descendente las dietas más usadas y por cuantos pacientes se está usando cada una de ellas, solo deben aparecer las que estén en uso.


public class Menu {
    public static void main(String[] args) {
        DietProgram program = new DietProgram();
        program.showMenuProgram();
    }
}
