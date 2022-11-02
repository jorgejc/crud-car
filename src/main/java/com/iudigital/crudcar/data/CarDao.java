package com.iudigital.crudcar.data;

import com.iudigital.crudcar.dominio.Car;
import com.iudigital.crudcar.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private static final String GET_CARS = "select * from car";

    private static final String CREATE_CAR = "insert into car"
            + "(marca, modelo, anho, color, transmission) "
            + "values (?, ?, ?, ?, ?)";

    private static final String GET_CAR_BY_ID = "select * from car where id = ?";

    private static final String UPDATE_CAR = "update car set marca = ?, modelo = ?, "
            + "anho = ?, color = ?, transmission = ? where id = ?";

    private static final String DELETE_CAR = "delete from car where id = ? ";

    public void crear(Car car) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_CAR);
            preparedStatement.setString(1, car.getMarca());
            preparedStatement.setString(2, car.getModelo());
            preparedStatement.setString(3, car.getAnho());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setString(5, car.getTransmission());
            preparedStatement.executeUpdate();

        } finally {
            if (connection != null) {
                connection.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    public List<Car> obtenerCarros() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> cars = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS);
            resultSet = preparedStatement.executeQuery();
            
            while ( resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMarca(resultSet.getString("Marca"));
                car.setModelo(resultSet.getString("Modelo"));
                car.setAnho(resultSet.getString("Anho"));
                car.setColor(resultSet.getString("Color"));
                car.setTransmission(resultSet.getString("Transmission"));
                cars.add(car);
            }
            return cars;
            
        } finally {
            if (connection != null) {
                connection.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
    public Car obtenerCar(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMarca(resultSet.getString("Marca"));
                car.setModelo(resultSet.getString("Modelo"));
                car.setAnho(resultSet.getString("Anho"));
                car.setColor(resultSet.getString("Color"));
                car.setTransmission(resultSet.getString("Transmission"));
            }
            return car;
            
        } finally {
            if (connection != null) {
                connection.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
    public void actualizarCarro(int id, Car car) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CAR);
            preparedStatement.setString(1, car.getMarca());
            preparedStatement.setString(2, car.getModelo());
            preparedStatement.setString(3, car.getAnho());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setString(5, car.getTransmission());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        } finally {
            if (connection != null) {
                connection.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    public void eliminarCarro(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CAR);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } finally {
            if (connection != null) {
                connection.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            } 
        }
    }
}
