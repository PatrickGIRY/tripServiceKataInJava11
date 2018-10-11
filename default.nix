with import <nixpkgs> {};
let 
mvn = maven.override { jdk="/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home"; };
in
stdenv.mkDerivation {
   name = "mvn-java11";
   JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home";
   buildInputs = [mvn];
}


