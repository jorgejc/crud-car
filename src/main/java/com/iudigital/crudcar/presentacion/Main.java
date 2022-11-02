package com.iudigital.crudcar.presentacion;

import com.iudigital.crudcar.controller.CarController;
import com.iudigital.crudcar.dominio.Car;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void obtenerCarros(CarController carController) {
        try {
            List<Car> cars = carController.obtenerCarros();
            if (cars.isEmpty()) {
                System.out.println("No hay Datos");
            } else {
                cars.forEach(car -> {
                    System.out.println("id: " + car.getId());
                    System.out.println("Marca: " + car.getMarca());
                    System.out.println("Modelo: " + car.getModelo());
                    System.out.println("Año: " + car.getAnho());
                    System.out.println("Color: " + car.getColor());
                    System.out.println("Transmission: " + car.getTransmission());
                    System.out.println("---------------------");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void crearCar(CarController carController) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite la marca: ");
            String marca = sc.nextLine();
            System.out.println("Marca es: " + marca);
            System.out.println("--------------- ");

            System.out.println("Digite el modelo: ");
            String modelo = sc.nextLine();
            System.out.println("el modelo es: " + modelo);
            System.out.println("--------------- ");

            System.out.println("Digite el anho: ");
            String anho = sc.nextLine();
            System.out.println("El año del modelo es: " + anho);
            System.out.println("---------------------");

            System.out.println("Digite el color: ");
            String color = sc.nextLine();
            System.out.println("color: " + color);
            System.out.println("--------------- ");

            System.out.println("Digite tipo de transmision: ");
            String transmission = sc.nextLine();
            System.out.println("transmission es: " + transmission);
            System.out.println("--------------- ");

            Car car = new Car();
            car.setMarca(marca);
            car.setModelo(modelo);
            car.setAnho(anho);
            car.setColor(color);
            car.setTransmission(transmission);
            carController.crear(car);
            System.out.println("Carrro creado con exito: ");
            obtenerCarros(carController);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void obtenerCar(CarController carController) {

        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Digite el id: ");
            int id = sc.nextInt();
            System.out.println("id: " + id);
            System.out.println("-------------------------- ");
            
            Car car = carController.obtenerCar(id);
            System.out.println("Listar carro = " + car);
            System.out.println("-------------------------------------------- ");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void editCar(CarController carController) {
        try{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Digite ID del carro: " );
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("id es: " + id);
                Car carExits = carController.obtenerCar(id);
                if(carExits == null){
                    System.out.println("No existe carro ");
                    return;
                }
                
                
                System.out.println("Digite la marca: ");
                String marca = sc.nextLine();
                System.out.println("La marca es: " + marca);
                
                System.out.println("Digite el modelo: " );
                String modelo = sc.nextLine();
                System.out.println("model = " + modelo);
                System.out.println("---------------------");
                
                System.out.println("Digite el anho: ");
                String anho = sc.nextLine();
                System.out.println("El año del modelo es: " + anho);
                System.out.println("---------------------");
                
                System.out.println("Digite el color: ");
                String color = sc.nextLine();
                System.out.println("color: " + color);
                System.out.println("--------------- ");
                
                System.out.println("Digite tipo de transmision: ");
                String transmission = sc.nextLine();
                System.out.println("transmission es: " + transmission);
                System.out.println("--------------- ");
                
                Car car = new Car();
                car.setMarca(marca);
                car.setModelo(modelo);
                car.setAnho(anho);
                car.setColor(color);
                car.setTransmission(transmission);
                carController.actualizarCarro(id, car);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }
    
    public static void eliminarCarro(CarController carController) {
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Digite el ID del carro: ");
            int id = sc.nextInt();
            System.out.println("El id del carro es:" + id);
            Car carExists = carController.obtenerCar(id);
            if(carExists == null) {
                System.out.println("No existe Carro" );
                return;
            } 
            
            carController.eliminarCarro(id);
            System.out.println("Carro eliminado");
            obtenerCarros(carController);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CarController carController = new CarController();
        //obtenerCarros(carController);
        //obtenerCar(carController);
        //crearCar(carController);
       //editCar(carController);
        //eliminarCarro(carController);
        
        var opcion = -1;
        var scanner = new Scanner(System.in);
        while(opcion != 0){
            System.out.println("----------------------------------");
            System.out.println("ELIGE UNA OPCIÓN");
            System.out.println("----------------------------------");
            
            System.out.println("1. Listar Carros "  );        
            System.out.println("2. Crear Carro");
            System.out.println("3. Listar por Id");
            System.out.println("4. Editar Carro");
            System.out.println("5. Eliminar Carro");
            
            System.out.println("----------------------------------");
            
            opcion = Integer.parseInt(scanner.nextLine());
            switch(opcion){
                case 0:
                    System.out.println("Ha salido de la aplicación ");
                    break;
                case 1:
                    obtenerCarros(carController);
                    break;
                case 2:
                    crearCar(carController);
                    break;
                case 3:
                    obtenerCar(carController);
                    break;
                case 4:
                    editCar(carController);
                    break;
                case 5:
                    eliminarCarro(carController);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    
    }
}
