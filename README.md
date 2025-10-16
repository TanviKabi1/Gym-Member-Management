<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>GymSwingApp - README</title>
</head>
<body>
  <h1>GymSwingApp - Gym Member Management Made Simple</h1>

  <p>A simple desktop application using Java Swing and MySQL for efficient gym membership management.</p>

  <h2>📱 About</h2>
  <p>GymSwingApp lets you add, update, delete, and view member records in a clean Java Swing interface, persisting changes to a MySQL database.</p>

  <h2>⚡️ Key Features</h2>
  <ul>
    <li>Member management: Add, update, and delete gym member records</li>
    <li>Table view: Display members in a JTable with sorting and editing</li>
    <li>Database integration: Connects to MySQL for storage</li>
    <li>Input validation: Checks for numeric and text entries</li>
    <li>User-friendly UI: Responsive Swing layout, clear forms</li>
    <li>Error feedback: Dialog alerts for invalid input or database errors</li>
  </ul>

  <h2>🛠 Tech Stack</h2>
  <ul>
    <li>Java Swing</li>
    <li>MySQL</li>
    <li>JDBC</li>
  </ul>

  <h2>🚀 Getting Started</h2>
  <h3>Prerequisites</h3>
  <ul>
    <li>Java 8 or higher</li>
    <li>MySQL installed and running</li>
  </ul>

  <h3>Setup</h3>
  <ol>
    <li>Clone the repo:<br><code>git clone &lt;repo-url&gt;</code></li>
    <li>Open project in your IDE</li>
    <li>Configure <code>jdbc:mysql://localhost:3306/yourdb</code> in the code</li>
    <li>Run <code>GymSwingApp.java</code></li>
  </ol>

  <h2>📋 Usage</h2>
  <ol>
    <li>Enter member details: ID, Name, Type, Fees</li>
    <li>Use "Add Member" to save</li>
    <li>Edit fields to update, or delete a member</li>
    <li>See all members in the table below</li>
  </ol>

  <h2>🏗 Project Structure</h2>
  <pre>
GymSwingApp/
├── GymSwingApp.java
├── (other Java files)
└── resources/
  </pre>

  <h2>🔧 Configuration</h2>
  <p>Edit your MySQL credentials and test the connection in the code:</p>
  <pre>
String url = "jdbc:mysql://localhost:3306/test";
String user = "root";
String password = "yourpassword";
  </pre>

  <h2>🎨 Core Features</h2>
  <table border="1">
    <tr><th>Feature</th><th>Status</th></tr>
    <tr><td>Member add/update/delete</td><td>✅</td></tr>
    <tr><td>JTable display</td><td>✅</td></tr>
    <tr><td>MySQL persistence</td><td>✅</td></tr>
    <tr><td>Error dialogs</td><td>✅</td></tr>
  </table>

  <h2>📝 License</h2>
  <p>This project is MIT licensed.</p>

  <h2>🔮 Future Enhancements</h2>
  <ul>
    <li>Export member list to PDF</li>
    <li>User authentication</li>
    <li>Search/filter functionality</li>
  </ul>
</body>
</html>
