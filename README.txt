# Clarity Stocks README

Welcome to Clarity Stocks, the intuitive and streamlined stock tracking tool designed to make stock market analysis accessible and understandable for everyone, from beginners to experienced investors. This document provides an overview of the product, key features, and guidance on how to use Clarity Stocks to enhance your investing experience.

## Product Overview

Clarity Stocks offers a clear and minimalistic interface that simplifies the process of tracking and analyzing stock performance. With our easy-to-use platform, users can quickly find specific stocks using the search functionality or revisit previously viewed stocks from a convenient list.

### Key Features

- **Simple Interface**: A user-friendly dashboard that provides quick access to all essential functionalities.
- **Stock Search**: Easily locate specific stocks with our efficient search feature or select from a list of stocks you've viewed before.
- **Price Chart Visualization**: View real-time and historical stock price data with adjustable time intervals (daily, weekly, monthly, annually) to better understand stock performance over time.
- **Focused Data Presentation**: Unlike platforms that clutter your screen with too much information, Clarity Stocks presents only the fundamental metrics of stock viability and company performance in an easy-to-understand format.
- **Advanced Analysis on Demand**: For deeper insight, users can select more complex analyses, which are then displayed through a dedicated button above the price chart. These analyses are explained in detail in their respective sections within the tool.

### How to Use Clarity Stocks

1. **Building the project**: Build the project from the root folder and import Maven sources.
2. **Running the program**: Run the class MainLauncher located in the module clarity-stocks-launcher.
3. **Searching for stocks**: Search for a stock in the search bar located in the top-right corner of the application.
4. **Viewing stock data**: Select a stock from the search results to view its real-time and historical price data.
5. **Analyzing stock performance**: Utilize the various analysis options available to gain deeper insights into stock performance and company metrics (currently GoldenCross by pressing the button above the graph + evaluations).

### API Usage

- **API Key**: The included API key is a free version with limited API calls per hour.
- **Ownership**: The key is intentionally included for testing purposes.

### GitHub Repository

- **URL**: https://github.com/JoarEliasson/ClarityStocks

### Database Instructions

1. **Creating the Database**: Set up a postgreSQL database server.
2. **Setting up the tables**: Use the SQL script from tables.sql in the clarity-stocks-data module under resources to create the necessary tables.
3. **Configuring the Database**: Update the database connection details in the application.properties file located in the clarity-stocks-data module under resources. (For the url, make sure to write the jDBC url to your database).
4. **Adding market and stock data**: Run the DataInitializer class in the clarity-stocks-data module to populate the database with market and stock data.