package hu.griddy.kotlinbasics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.resourceinspection.annotation.Attribute.IntMap

class MainActivity : AppCompatActivity() {

    private lateinit var openCalculator_Button: Button;
    private lateinit var openGreeting_Button: Button;
    private lateinit var openWeather_Button: Button;
    private lateinit var openUsers_Button: Button;
    private lateinit var openRandomUsers_Button: Button;
    private lateinit var openStudnets_Button: Button;
    private lateinit var openColorListButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        data class Item(
            val itemId:String,
            val productName:String,
            val quantity:Int,
            val pricePerUnit:Double,
            val discount:Double
        )

        data class Customer(
            val customerId:String,
            val name:String,
            val email:String,
            val phone:String
        )

        data class Address(
            val street:String,
            val city:String,
            val postalCode:String,
            val country:String
        )
        data class Shipping(
            val address:Address,
            val shippingMethod:String,
            val estimatedDeliveryDays:Int
        )

        data class Payment(
            val paymentMethod:String,
            val totalAmmount:Double,
            val paid:Boolean
        )

        data class Order(
            val customer:Customer,
            val orderItems:List<Item>,
            val shipping:Shipping,
            val payment:Payment,
            val status:String
        )



        data class StudentScores(
            val STU123:String,
            val STU124:String,
            val STU125:String
        )
        data class Assignment(
            val assignmentName:String,
            val maxPoints:Int,
            val studentScores:StudentScores
        )

        data class TeamMember(
            val studentId:String,
            val name:String,
            val grade:Int,
            val hasCompleted:Boolean
        )

        data class Project(
            val projectName:String,
            val subject:String,
            val durationWeeks:Int,
            val teamMembers:List<TeamMember>,
            val assignments:List<Assignment>,
            val completed:Boolean
        )



        data class CurrentLocation(
            val latitude:Double,
            val longtitude:Double
        )
        data class Vehicle(
            val vehicleId:String,
            val capacity:Int,
            val currentLocation: CurrentLocation,
            val status:String
        )

        data class TimeFrame(
            val departureTime:String,
            val arrivalTime:String
        )
        data class Schedule(
            val weekday:List<TimeFrame>,
            val weekend:List<TimeFrame>
        )

        data class TransitVehicle(
            val routeNumber:String,
            val routeName:String,
            val vehicles:List<Vehicle>,
            val schedule: Schedule
        )
        data class TransistSystem(
            val buses:List<TransitVehicle>,
            val trams:List<TransitVehicle>
        )

        data class EmergencyContact(
            val name:String,
            val phone:String,
            val shift:String
        )
        data class ControlCenter(
            val contactNumber:String,
            val emergencyContacts:List<EmergencyContact>
        )

        data class PublicTransport(
            val cityName:String,
            val transitSystem:TransistSystem,
            val controlCenter: ControlCenter
        )

        openCalculator_Button = findViewById(R.id.OpenCalculator_Button);
        openGreeting_Button = findViewById(R.id.OpenGreeting_Button);
        openWeather_Button = findViewById(R.id.OpenWeather_Button);
        openUsers_Button = findViewById(R.id.OpenUsers_Button);
        openRandomUsers_Button = findViewById(R.id.OpenRandomUsers_Button);
        openStudnets_Button = findViewById(R.id.OpenStudents_Button);
        openColorListButton = findViewById(R.id.openColorListButton);

        openCalculator_Button.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        openGreeting_Button.setOnClickListener{
            val intent = Intent(this, GreetingActivity::class.java);
            startActivity(intent);
        }

        openWeather_Button.setOnClickListener{
            val intent = Intent(this, WeatherActivity::class.java);
            startActivity(intent);
        }

        openUsers_Button.setOnClickListener{
            val intent = Intent(this, UserListActivity::class.java);
            startActivity(intent);
        }

        openRandomUsers_Button.setOnClickListener{
            val intent = Intent(this, RandomUserListActivity::class.java);
            startActivity(intent);
        }

        openStudnets_Button.setOnClickListener{
            val intent = Intent(this, StudentsActivity::class.java);
            startActivity(intent);
        }

        openColorListButton.setOnClickListener{
            val intent = Intent(this, ColorListActivity::class.java);
            startActivity(intent);
        }
    }
} //test
