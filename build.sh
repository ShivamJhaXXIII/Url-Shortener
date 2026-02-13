#!/usr/bin/env bash
# Build script for Render

echo "Building application..."

# Install Maven if not present
if ! command -v mvn &> /dev/null
then
    echo "Maven not found, installing..."
    # Render will use the maven image
fi

# Build the application
mvn clean package -DskipTests

echo "Build complete!"

