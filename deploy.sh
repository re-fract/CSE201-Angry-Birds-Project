#!/bin/bash
# Deployment preparation script for Netlify

echo "Building Angry Birds for web deployment..."

# Build the project
./gradlew html:dist

echo "Build complete! Files ready for deployment in html/build/dist/"
echo ""
echo "Deployment files:"
ls -la html/build/dist/

echo ""
echo "Ready for Netlify deployment!"
