// firebase.ts
import { initializeApp } from "firebase/app";
import { getFirestore, initializeFirestore, persistentLocalCache, persistentMultipleTabManager } from "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyDgcB-RkaB1HaCD6Z0gcuf8jnWgrdyM7M8",
  authDomain: "database-access-6875f.firebaseapp.com",
  projectId: "database-access-6875f",
  storageBucket: "database-access-6875f.firebasestorage.app",
  messagingSenderId: "345530206048",
  appId: "1:345530206048:web:82440553adcd1a23fd4005",
  measurementId: "G-WGTTBLSD16",
};

// Initialize Firebase with better offline support and multi-tab handling
const app = initializeApp(firebaseConfig);

// Initialize Firestore with persistence and multi-tab support
initializeFirestore(app, {
  localCache: persistentLocalCache({
    tabManager: persistentMultipleTabManager()
  })
});

const db = getFirestore(app);

export { db };