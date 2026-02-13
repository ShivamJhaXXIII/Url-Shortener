#!/usr/bin/env bash
# Start script for Render deployment

set -e

echo "Starting URL Shortener application..."
echo "Java Version:"
java -version

echo "Starting application with PORT=$PORT"

# Start the application with production profile
java -Dserver.port=$PORT \
     -Dspring.profiles.active=prod \
     -jar target/shorten_url-0.0.1-SNAPSHOT.jar

