// app/(tabs)/explore.tsx
import { StyleSheet, View, Text, FlatList, ActivityIndicator } from 'react-native';
import { useEffect, useState } from 'react';
import { collection, query, orderBy, onSnapshot } from 'firebase/firestore';
import { db } from '../../firebase';

interface Student {
  id: string;
  matricula: string;
  nombre: string;
  semestre: string;
}

export default function ListScreen() {
  const [students, setStudents] = useState<Student[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setLoading(true);
    const q = query(collection(db, 'estudiantes'), orderBy('timestamp', 'desc'));
    
    const unsubscribe = onSnapshot(q, 
      (querySnapshot) => {
        const studentsArray: Student[] = [];
        querySnapshot.forEach((doc) => {
          studentsArray.push({
            id: doc.id,
            ...doc.data() as Omit<Student, 'id'>
          });
        });
        setStudents(studentsArray);
        setLoading(false);
        setError(null);
      },
      (error) => {
        console.error('Error fetching students:', error);
        setError('Error al cargar los estudiantes. Por favor, intenta de nuevo.');
        setLoading(false);
      }
    );

    return () => unsubscribe();
  }, []);

  const renderItem = ({ item }: { item: Student }) => (
    <View style={styles.itemContainer}>
      <Text style={styles.itemText}>{item.matricula}, {item.nombre}, {item.semestre}</Text>
    </View>
  );

  if (loading) {
    return (
      <View style={styles.centerContainer}>
        <ActivityIndicator size="large" color="#6200ee" />
        <Text style={styles.loadingText}>Cargando estudiantes...</Text>
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.centerContainer}>
        <Text style={styles.errorText}>{error}</Text>
      </View>
    );
  }

  if (students.length === 0) {
    return (
      <View style={styles.centerContainer}>
        <Text style={styles.emptyText}>No hay estudiantes registrados</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <FlatList
        data={students}
        renderItem={renderItem}
        keyExtractor={item => item.id}
        contentContainerStyle={styles.listContent}
        ListHeaderComponent={
          <Text style={styles.headerText}>
            {students.length} {students.length === 1 ? 'estudiante' : 'estudiantes'} registrados
          </Text>
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#fff',
  },
  centerContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#fff',
  },
  listContent: {
    paddingBottom: 20,
  },
  itemContainer: {
    padding: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    backgroundColor: '#f8f9fa',
    marginBottom: 10,
    borderRadius: 8,
    elevation: 2,
  },
  itemText: {
    fontSize: 16,
  },
  loadingText: {
    marginTop: 10,
    fontSize: 16,
    color: '#666',
  },
  errorText: {
    color: '#dc3545',
    fontSize: 16,
    textAlign: 'center',
  },
  emptyText: {
    fontSize: 16,
    color: '#666',
    textAlign: 'center',
  },
  headerText: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 15,
    color: '#333',
  },
});