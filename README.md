# 🧠 Text Summarizer API using Ollama (Mistral) + Spring Boot

This project exposes a simple REST API built with **Spring Boot** that accepts a block of text and returns a **summary** using the **Mistral model from Ollama** running locally.

---

## 🚀 Features

- 🔗 Local inference using Ollama's Mistral model (no API keys needed)
- ⚡ Fast and lightweight summarization
- 🌱 Built using Java & Spring Boot

---

## 📦 Prerequisites

Make sure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- [Ollama](https://ollama.com/)
- Git

---

## 🛠️ Steps to Setup & Run

### 🔧 Step 1: Download and Install Ollama

```bash
# For macOS (Apple Silicon or Intel)
brew install ollama

# OR download manually from:
https://ollama.com/download


## Pull the Mistral Model

command - ollama pull mistral

## run the mistral model locally with command - ollama run mistral

Run the Spring Boot App

./mvnw spring-boot:run

## hit this curl in postman

curl --location 'http://localhost:8080/api/summarize' \
--header 'Content-Type: application/json' \
--data '{
  "text": "In a payment landscape where physical banknotes, cash on delivery or cheque payments are a thing of the past, ensuring that your customer-facing business supports a digital payment solution that is safe and seamless is an imperative. \n\nIn India, the diverse population, straddling multiple demographic and low-resource scenarios, has seen digital payments applications face some challenges. This includes factors like lack of trust, digital literacy, fiscal-savviness, connectivity and integration issues. \n\nTo combat the large spectrum of issues faced, National Payments Corporation of India (NPCI) committed to enhancing and upgrading BHIM; India’s digital payment platform. The BHIM app heralded the adoption of UPI (Unified Payment Interface) and completely disrupted the entire payments landscape in India. It provides an inclusive, robust, intuitive, scalable and adaptive digital payment solution. Today UPI supports transactions of up to 12 lakh crore a month.\n\nIn this digital endeavor, NPCI partnered with Thoughtworks for exceptional engineering and agile delivery. Thoughtworks combined its expertise across product design, business analysis and technology to help NPCI reimagine and build India’s first largest payment application. We redesigned the user interface and rebuilt the BHIM app on an open platform using React Native. \n\nOur approach helped eliminate friction in the user’s experience. We developed intuitive customer onboarding journeys, upgraded user interface and fixed transaction latency issues in the BHIM app. This, along with the enhanced backend processing of several million transactions, helped NPCI achieve and maintain greater user adoption. \n\nWe also helped NPCI achieve and maintain greater user adoption by developing intuitive customer onboarding journeys, providing an upgraded user interface and fixing transaction latency issues in the BHIM app. Today, the app sees more than 8 billion transactions a month and no less than 100,000 a day. BHIM is one of the top five apps to send and receive money in India. Our partnership with NPCI (and other global public sector clients) has been instrumental in ensuring Thoughtworks is the only IT services provider in the Digital Public Goods Alliance.\n\nThe app needed migration from a less scalable Mystique framework to a highly scalable React-Native framework, using a strangler approach, and is powered by an upgraded tech stack to support a highly scalable platform. \n\nUnique to the collaboration was stringent timelines that meant NPCI and Thoughtworks had to ensure revamped BHIM was available for citizens in time for its third anniversary."
}
'


2nd api curl to upload a document and get summary of document

curl --location 'http://localhost:8080/api/upload-and-get-summary' \
--form 'file=@"postman-cloud:///1f06cb2b-834c-4550-98d4-4908aec8cce0"'
