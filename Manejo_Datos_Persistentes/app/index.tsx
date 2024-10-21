import React, { useState, useEffect } from "react";
import { Image, StyleSheet, View } from "react-native";
import Slider from "@react-native-community/slider";
import AsyncStorage from "@react-native-async-storage/async-storage";

import { HelloWave } from "@/components/HelloWave";
import ParallaxScrollView from "@/components/ParallaxScrollView";
import { ThemedText } from "@/components/ThemedText";
import { ThemedView } from "@/components/ThemedView";

export default function HomeScreen() {
  const [red, setRed] = useState(0);
  const [green, setGreen] = useState(0);
  const [blue, setBlue] = useState(0);

  useEffect(() => {
    loadColors();
  }, []);

  useEffect(() => {
    saveColors();
  }, [red, green, blue]);

  const loadColors = async () => {
    try {
      const redValue = await AsyncStorage.getItem("redValue");
      const greenValue = await AsyncStorage.getItem("greenValue");
      const blueValue = await AsyncStorage.getItem("blueValue");

      if (redValue !== null) setRed(parseFloat(redValue));
      if (greenValue !== null) setGreen(parseFloat(greenValue));
      if (blueValue !== null) setBlue(parseFloat(blueValue));
    } catch (error) {
      console.error("Error al cargar los colores:", error);
    }
  };

  const saveColors = async () => {
    try {
      await AsyncStorage.setItem("redValue", red.toString());
      await AsyncStorage.setItem("greenValue", green.toString());
      await AsyncStorage.setItem("blueValue", blue.toString());
    } catch (error) {
      console.error("Error saving colors:", error);
    }
  };

  const backgroundColor = `rgb(${Math.round(red * 255)}, ${Math.round(
    green * 255
  )}, ${Math.round(blue * 255)})`;

  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: backgroundColor, dark: backgroundColor }}
      headerImage={
        <Image
          source={require("@/assets/images/partial-react-logo.png")}
          style={styles.reactLogo}
        />
      }
    >
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Bienvenido Profe! 🗿</ThemedText>
        <HelloWave />
      </ThemedView>
      <ThemedView style={styles.stepContainer}>
        <ThemedText type="subtitle">Selector de Color</ThemedText>
        <View style={styles.sliderContainer}>
          <ThemedText>Rojo: {Math.round(red * 255)}</ThemedText>
          <Slider
            style={styles.slider}
            minimumValue={0}
            maximumValue={1}
            value={red}
            onValueChange={setRed}
            minimumTrackTintColor="#ff0000"
          />
        </View>
        <View style={styles.sliderContainer}>
          <ThemedText>Verde: {Math.round(green * 255)}</ThemedText>
          <Slider
            style={styles.slider}
            minimumValue={0}
            maximumValue={1}
            value={green}
            onValueChange={setGreen}
            minimumTrackTintColor="#00ff00"
          />
        </View>
        <View style={styles.sliderContainer}>
          <ThemedText>Azul: {Math.round(blue * 255)}</ThemedText>
          <Slider
            style={styles.slider}
            minimumValue={0}
            maximumValue={1}
            value={blue}
            onValueChange={setBlue}
            minimumTrackTintColor="#0000ff"
          />
        </View>
      </ThemedView>
    </ParallaxScrollView>
  );
}

const styles = StyleSheet.create({
  titleContainer: {
    flexDirection: "row",
    alignItems: "center",
    gap: 8,
  },
  stepContainer: {
    gap: 8,
    marginBottom: 16,
  },
  reactLogo: {
    height: 178,
    width: 290,
    bottom: 0,
    left: 0,
    position: "absolute",
  },
  sliderContainer: {
    width: "100%",
    marginBottom: 10,
  },
  slider: {
    width: "100%",
  },
});
