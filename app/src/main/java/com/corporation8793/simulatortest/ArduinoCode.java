package com.corporation8793.simulatortest;

public class ArduinoCode {
    public static String chapter_code(String chapter_id){
        switch (chapter_id){
            case "3-2":
                return "void setup() {\n" +
                        "pinMode(8,OUTPUT);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "digitalWrite(8,HIGH);\n" +
                        "delay(1000);\n" +
                        "digitalWrite(8,LOW);\n" +
                        "delay(1000);\n" +
                        "}";
            case "3-3":
                return "void setup() {\n" +
                        "pinMode(8,OUTPUT);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "digitalWrite(8,HIGH);\n" +
                        "delay(500);\n" +
                        "digitalWrite(8,LOW);\n" +
                        "delay(200);\n" +
                        "}";
            case "3-4":
                return "void setup() {\n" +
                        "pinMode(13,OUTPUT);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "digitalWrite(13,HIGH);\n" +
                        "delay(200);\n" +
                        "digitalWrite(13,LOW);\n" +
                        "delay(400);\n" +
                        "}";

            case "5-2":
                return "void setup() {\n" +
                        "pinMode(7,INPUT);\n" +
                        " Serial.begin(9600);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "if(digitalRead(7)==true){\n" +
                        "  Serial.println(\"ON\");  \n" +
                        "}else{\n" +
                        "  Serial.println(\"OFF\");\n" +
                        "}\n" +
                        "delay(100);\n" +
                        "}";
            case "5-3":
                return "void setup() {\n" +
                        "pinMode(5,INPUT);\n" +
                        " Serial.begin(9600);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "if(digitalRead(5)==true){\n" +
                        "  Serial.println(\"ON\");  \n" +
                        "}else{\n" +
                        "  Serial.println(\"OFF\");\n" +
                        "}\n" +
                        "delay(100);\n" +
                        "}";
            case "5-4":
                return "void setup() {\n" +
                        "pinMode(5,INPUT);\n" +
                        "pinMode(10,OUTPUT);\n" +
                        " Serial.begin(9600);\n" +
                        "}\n" +
                        "void loop() {\n" +
                        "if(digitalRead(5)==true){\n" +
                        "  Serial.println(\"ON\");\n" +
                        "  digitalWrite(10,HIGH);  \n" +
                        "}else{\n" +
                        "  Serial.println(\"OFF\");\n" +
                        "  digitalWrite(10,LOW);\n" +
                        "}\n" +
                        "delay(100);\n" +
                        "}";

        }

        return "0";
    }
}
