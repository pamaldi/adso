# ADSO

ADSO is a Java Quarkus project focused on experiments with LLM agents. The project comprises two main modules: adso-parent and adso-core. It leverages the Langchaing4j library for advanced language processing capabilities.
LLM agents are software entities that use Large Language Models (LLMs) to understand, process, and generate human language. They are used in applications like chatbots, virtual assistants, and language translation systems to perform advanced language tasks.
## Table of Contents

- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Building and Running](#building-and-running)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Structure

The ADSO project is structured into two main modules:

### adso-parent

This is the parent module that manages dependencies and configurations for the entire project.

### adso-core

This is the core module where the main logic and experiments with LLM agents are implemented.

## Technologies Used

- Java
- Quarkus
- Langchaing4j
- Maven

## Getting Started

### Prerequisites

Ensure you have the following installed:
- Java 11 or higher
- Maven 3.6.3 or higher

### Clone
    
    git clone <repository-url>
    cd adso-project


## Building and Running
### Build the Project
Navigate to the root directory of the project and build the modules using Maven:

    
    mvn clean install

## Usage
### LLM Experiments
The adso-core module contains various experiments with LLM agents using the Langchaing4j library. To interact with these experiments, you can use the provided REST endpoints.
Example Endpoint
You can access the endpoints via http://localhost:8080/api/experiment. Specific endpoints and their usage will be documented within the code and API documentation.

## Contributing
We welcome contributions! Please follow these steps to contribute:
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature-branch).
Create a new Pull Request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
Feel free to reach out for any questions or support regarding the ADSO project. Enjoy experimenting with LLM agents using Quarkus and Langchaing4j!

