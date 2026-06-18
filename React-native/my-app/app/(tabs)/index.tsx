// import { View, Text, StyleSheet } from 'react-native'
// import React from 'react'

// const app = () => {
//   return (
//     <View>
//       <Text style={styles.container}>index</Text>
//     </View>
//   )
// }

// const styles = StyleSheet.create({
//   container:{
//     // back air12242
//     backgroundColor:"#ffff",
//     height:100,
//     width:100,
//   }
// });
// export default app
import React, { useState, useEffect } from 'react';
// import { View, Text, TextInput, TouchableOpacity, FlatList, StyleSheet, Alert } from 'react-native';

// interface Student {
//   _id: string;
//   name: string;
//   email: string;
//   age: string;
//   className: string;
// }

// export default function App() {
//   const [name, setName] = useState('');
//   const [email, setEmail] = useState('');
//   const [age, setAge] = useState('');
//   const [className, setClassName] = useState('');
//   const [students, setStudents] = useState<Student[]>([]);

//   // Fetch students from the backend when the component mounts
//   useEffect(() => {
//     fetch('http://localhost:5000/api/students') // Replace with your backend URL
//       .then((response) => response.json())
//       .then((data) => setStudents(data))
//       .catch((error) => console.error('Error fetching students:', error));
//   }, [students]);

//   const handleAddStudent = () => {
//     if (!name || !email || !age || !className) {
//       Alert.alert('Please fill in all fields');
//       return;
//     }

//     const newStudent: Student = { name, email, age, className };

//     console.log(newStudent+" ......");
//     // Make a POST request to add a new student
//     fetch('http://localhost:5000/api/students', { // Replace with your backend URL
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify(newStudent),
//     })
//       .then((response) => response.json())
//       .then((data) => {
//         setStudents((prevStudents) => [...prevStudents, data]);
//         // Clear form fields after adding the student
//         setName('');
//         setEmail('');
//         setAge('');
//         setClassName('');
//       })
//       .catch((error) => console.error('Error adding student:', error));
//   };

//   const renderStudent = ({ item }: { item: Student }) => (
//     <View style={styles.studentCard}>
//       <Text style={styles.studentText}>Name: {item.name}</Text>
//       <Text style={styles.studentText}>Email: {item.email}</Text>
//       <Text style={styles.studentText}>Age: {item.age}</Text>
//       <Text style={styles.studentText}>Class: {item.className}</Text>
//     </View>
//   );

//   return (
//     <View style={styles.container}>
//       <Text style={styles.header}>Add a Student</Text>

//       <TextInput
//         style={styles.input}
//         placeholder="Name"
//         value={name}
//         onChangeText={setName}
//       />
//       <TextInput
//         style={styles.input}
//         placeholder="Email"
//         value={email}
//         onChangeText={setEmail}
//       />
//       <TextInput
//         style={styles.input}
//         placeholder="Age"
//         value={age}
//         onChangeText={setAge}
//         keyboardType="numeric"
//       />
//       <TextInput
//         style={styles.input}
//         placeholder="Class (e.g., MCA, BCA, BTech)"
//         value={className}
//         onChangeText={setClassName}
//       />

//       <TouchableOpacity style={styles.addButton} onPress={handleAddStudent}>
//         <Text style={styles.addButtonText}>Add Student</Text>
//       </TouchableOpacity>

//       <Text style={styles.header}>Students List</Text>
//       {students.length === 0 ? (
//         <Text>No students added yet.</Text>
//       ) : (
//         <FlatList
//           data={students}
//           renderItem={renderStudent}
//           keyExtractor={(item) => item._id}
//         />
//       )}
//     </View>
//   );
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     padding: 20,
//     backgroundColor:"white"
//   },
//   header: {
//     fontSize: 24,
//     fontWeight: 'bold',
//     marginBottom: 20,
//   },
//   input: {
//     height: 40,
//     borderColor: '#ccc',
//     borderWidth: 1,
//     marginBottom: 10,
//     paddingLeft: 10,
//     borderRadius: 4,
//   },
//   addButton: {
//     backgroundColor: '#007bff',
//     padding: 10,
//     borderRadius: 8,
//     marginVertical: 10,
//     alignItems: 'center',
//   },
//   addButtonText: {
//     color: '#fff',
//     fontSize: 16,
//     fontWeight: 'bold',
//   },
//   studentCard: {
//     backgroundColor: '#f9f9f9',
//     padding: 15,
//     borderRadius: 8,
//     marginBottom: 10,
//   },
//   studentText: {
//     fontSize: 16,
//     marginBottom: 5,
//   },
// });
// import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, FlatList, StyleSheet, Alert } from 'react-native';

interface Student {
  _id: string;
  name: string;
  email: string;
  age: string;
  course: string;  // Changed from className to course
}

export default function App() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [age, setAge] = useState('');
  const [course, setCourse] = useState('');  // Changed from className to course
  const [students, setStudents] = useState<Student[]>([]);

  // Fetch students from the backend when the component mounts
  useEffect(() => {
    fetch('http://localhost:5000/api/students') // Replace with your backend URL
      .then((response) => response.json())
      .then((data) => setStudents(data))
      .catch((error) => console.error('Error fetching students:', error));
  }, []);

  const handleAddStudent = () => {
    if (!name || !email || !age || !course) {  // Changed className to course
      Alert.alert('Please fill in all fields');
      return;
    }

    const newStudent: Student = { name, email, age, course };  // Changed className to course

    // console.log(newStudent + " ......");
    // Make a POST request to add a new student
    fetch('http://localhost:5000/api/students', { // Replace with your backend URL
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newStudent),
    })
      .then((response) => response.json())
      .then((data) => {
        setStudents((prevStudents) => [...prevStudents, data]);
        // Clear form fields after adding the student
        setName('');
        setEmail('');
        setAge('');
        setCourse('');  // Changed from className to course
      })
      .catch((error) => console.error('Error adding student:', error));
  };

  const renderStudent = ({ item }: { item: Student }) => (
    <View style={styles.studentCard}>
      <Text style={styles.studentText}>Name: {item.name}</Text>
      <Text style={styles.studentText}>Email: {item.email}</Text>
      <Text style={styles.studentText}>Age: {item.age}</Text>
      <Text style={styles.studentText}>Course: {item.course}</Text>  {/* Changed className to course */}
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Add a Student</Text>

      <TextInput
        style={styles.input}
        placeholder="Name"
        value={name}
        onChangeText={setName}
      />
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        style={styles.input}
        placeholder="Age"
        value={age}
        onChangeText={setAge}
        keyboardType="numeric"
      />
      <TextInput
        style={styles.input}
        placeholder="Course (e.g., MCA, BCA, BTech)"  // Changed className to course
        value={course}
        onChangeText={setCourse}  // Changed className to course
      />

      <TouchableOpacity style={styles.addButton} onPress={handleAddStudent}>
        <Text style={styles.addButtonText}>Add Student</Text>
      </TouchableOpacity>

      <Text style={styles.header}>Students List</Text>
      {students.length === 0 ? (
        <Text>No students added yet.</Text>
      ) : (
        <FlatList
          data={students}
          renderItem={renderStudent}
          keyExtractor={(item) => item._id}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: "white",
  },
  header: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    marginBottom: 10,
    paddingLeft: 10,
    borderRadius: 4,
  },
  addButton: {
    backgroundColor: '#007bff',
    padding: 10,
    borderRadius: 8,
    marginVertical: 10,
    alignItems: 'center',
  },
  addButtonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  studentCard: {
    backgroundColor: '#f9f9f9',
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
  },
  studentText: {
    fontSize: 16,
    marginBottom: 5,
  },
});
