MAIN=ImageEditor
CLASSPATH=".:./txtlib.jar"

run: compile
	java -cp ${CLASSPATH} ${MAIN}
compile: ImageEditor.class
ImageEditor.class: ImageEditor.java
	javac -cp ${CLASSPATH} ${MAIN}.java

clean:
	-rm -f *.class *~
