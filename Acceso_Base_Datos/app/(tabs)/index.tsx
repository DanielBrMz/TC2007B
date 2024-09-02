// app/(tabs)/index.tsx
import { StyleSheet, View, TextInput, TouchableOpacity, Text, Alert } from 'react-native';
import { useState } from 'react';
import { collection, addDoc, serverTimestamp } from 'firebase/firestore';
import { db } from '../../firebase';
import { router } from 'expo-router';

export default function RegisterScreen() {
  const [matricula, setMatricula] = useState('');
  const [nombre, setNombre] = useState('');
  const [semestre, setSemestre] = useState('');

  const handleSubmit = async () => {
    if (!matricula || !nombre || !semestre) {
      Alert.alert('Error', 'Todos los campos son requeridos');
      return;
    }

    try {
      await addDoc(collection(db, 'estudiantes'), {
        matricula,
        nombre,
        semestre,
        timestamp: serverTimestamp(),
      });

      Alert.alert('Éxito', 'Estudiante registrado correctamente');
      setMatricula('');
      setNombre('');
      setSemestre('');
      router.push('/explore');
    } catch (error) {
      Alert.alert('Error', 'No se pudo registrar el estudiante');
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder="Matrícula"
        value={matricula}
        onChangeText={setMatricula}
      />
      <TextInput
        style={styles.input}
        placeholder="Nombre"
        value={nombre}
        onChangeText={setNombre}
      />
      <TextInput
        style={styles.input}
        placeholder="Semestre"
        value={semestre}
        onChangeText={setSemestre}
        keyboardType="numeric"
      />
      <TouchableOpacity style={styles.button} onPress={handleSubmit}>
        <Text style={styles.buttonText}>GRABAR</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#fff',
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 10,
    paddingHorizontal: 10,
    borderRadius: 5,
  },
  button: {
    backgroundColor: '#6200ee',
    padding: 15,
    borderRadius: 5,
    alignItems: 'center',
  },
  buttonText: {
    color: 'white',
    fontWeight: 'bold',
  },
});