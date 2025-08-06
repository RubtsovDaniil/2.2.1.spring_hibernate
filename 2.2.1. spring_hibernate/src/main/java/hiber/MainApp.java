package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        carService.addCar(new Car("BMW", 1));
        carService.addCar(new Car("BMW", 2));
        carService.addCar(new Car("BMW", 1));
        carService.addCar(new Car("BMW", 4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User usersWithCars = userService.findUserByCarModelAndSeries("BMW", 4);
        if (usersWithCars != null) {
            Car car = usersWithCars.getCar();
            System.out.println("Найден пользователь с автомобилем model " + car.getModel() + " и series " + car.getSeries());
            System.out.println("First Name = " + usersWithCars.getFirstName());
            System.out.println("Last Name = " + usersWithCars.getLastName());
            System.out.println("Email = " + usersWithCars.getEmail());
            System.out.println();
        } else {
            System.out.println("Не верные параметры запроса");
        }

        context.close();
    }
}
