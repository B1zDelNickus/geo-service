# GeoService test task specification

## Table of contents
1. [Task description](#task)
2. [ENV run instruction](#compose)
3. [Prepare test data](#data)
4. [Tryout API for feo service](#api)

## <a name="task"></a> 1. Task description
- [task](Evaluation Assignment-BackEnd-Java.pdf "go to PDF task file")

## <a name="compose"></a> 2. ENV set instruction
- `./set_env.sh` - to fill test data to working environment

## <a name="data"></a> 3. Test application
- `./run_stack.sh` - to run docker and application
- `./shutdown_stack.sh` - to shut down docker and application

## <a name="api"></a> 4. Tryout API for feo service
- `curl http://localhost:8071/api/search/data?yMin=46.9&yMax=47&xMin=15.0&xMax=16`