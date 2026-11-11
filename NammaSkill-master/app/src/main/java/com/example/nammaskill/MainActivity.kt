package com.example.nammaskill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Course(
    val name: String,
    val duration: String,
    val eligibility: String,
    val jobGuarantee: String
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme(
                colorScheme = lightColorScheme()
            ) {
                NammaSkillApp()
            }
        }
    }
}

@Composable
fun NammaSkillApp() {

    var currentScreen by remember { mutableStateOf("splash") }
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    val courses = listOf(
        Course("Electrician", "3 Months", "SSLC", "Yes"),
        Course("Mobile Repair", "6 Months", "PUC", "Yes"),
        Course("Sewing", "2 Months", "Any", "No"),
        Course("Coding", "6 Months", "PUC", "Yes"),
        Course("Welding", "3 Months", "SSLC", "Yes")
    )

    when (currentScreen) {

        "splash" -> {
            WelcomeScreen {
                currentScreen = "home"
            }
        }

        "home" -> {
            HomeScreen(courses) {
                selectedCourse = it
                currentScreen = "details"
            }
        }

        "details" -> {
            selectedCourse?.let {
                DetailScreen(
                    course = it,
                    onApplyClick = {
                        currentScreen = "apply"
                    },
                    onBack = {
                        currentScreen = "home"
                    }
                )
            }
        }

        "apply" -> {
            selectedCourse?.let {
                ApplyScreen(
                    course = it,
                    onBack = {
                        currentScreen = "home"
                    }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    onStart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "NAMMA-SKILL",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Empowering Rural Youth",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onStart
        ) {
            Text("Get Started")
        }
    }
}

@Composable
fun HomeScreen(
    courses: List<Course>,
    onCourseClick: (Course) -> Unit
) {

    var selectedFilter by remember { mutableStateOf("All") }

    val filteredCourses = when (selectedFilter) {

        "Short" -> courses.filter {
            it.duration.contains("2") || it.duration.contains("3")
        }

        "Long" -> courses.filter {
            it.duration.contains("6")
        }

        else -> courses
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "NAMMA-SKILL",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Training Opportunity Tracker",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                FilterButton("All", selectedFilter) {
                    selectedFilter = "All"
                }

                FilterButton("Short", selectedFilter) {
                    selectedFilter = "Short"
                }

                FilterButton("Long", selectedFilter) {
                    selectedFilter = "Long"
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Nearest Skill Center",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Government Skill Center - Madikeri",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Distance: 5 KM",
                        color = Color.Black
                    )

                    Text(
                        text = "Courses Available: Welding, Electrician, Coding",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Success Stories",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Ravi Kumar",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Completed Welding Course and now working in Bangalore.",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Notifications",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Get Alerts for New Course Batches",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { }
                    ) {
                        Text("Enable Notifications")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        items(filteredCourses) { course ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {
                        onCourseClick(course)
                    },
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = course.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Duration: ${course.duration}",
                        color = Color.Black
                    )

                    Text(
                        text = "Eligibility: ${course.eligibility}",
                        color = Color.Black
                    )

                    Text(
                        text = "Job Guarantee: ${course.jobGuarantee}",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onCourseClick(course)
                        }
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: String,
    onClick: () -> Unit
) {

    val color =
        if (selected == text)
            Color(0xFF64B5F6)
        else
            Color(0xFFE0E0E0)

    Box(
        modifier = Modifier
            .background(
                color,
                RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
    ) {

        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailScreen(
    course: Course,
    onApplyClick: () -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = course.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Duration: ${course.duration}",
            color = Color.Black
        )

        Text(
            text = "Eligibility: ${course.eligibility}",
            color = Color.Black
        )

        Text(
            text = "Job Guarantee: ${course.jobGuarantee}",
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "This vocational training program helps youth develop industry-ready practical skills and improve employment opportunities.",
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onApplyClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Now")
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Composable
fun ApplyScreen(
    course: Course,
    onBack: () -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var qualification by remember {
        mutableStateOf("")
    }

    var submitted by remember {
        mutableStateOf(false)
    }

    var interested by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            Text(
                text = "Apply for ${course.name}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = {
                    Text("Phone")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = qualification,
                onValueChange = {
                    qualification = it
                },
                label = {
                    Text("Qualification")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    submitted = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (submitted) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Candidate Summary",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color(0xFF0D47A1)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Name: $name",
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Phone: $phone",
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Qualification: $qualification",
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Course: ${course.name}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Application Submitted Successfully!",
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                interested = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("I Am Interested")
                        }

                        if (interested) {

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Trainer will contact you soon",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Home")
            }
        }
    }
}