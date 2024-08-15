<?php
$servername = "localhost";
$username = "root"; // Cambia esto si tienes un usuario diferente
$password = ""; // Cambia esto si tienes una contraseña
$dbname = "olympics";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$country = $_POST['country'];
$gold = $_POST['gold'];
$silver = $_POST['silver'];
$bronze = $_POST['bronze'];

$sql = "INSERT INTO medals (country, gold, silver, bronze) VALUES ('$country', $gold, $silver, $bronze)";

if ($conn->query($sql) === TRUE) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
