# Library Management System

## Overview

This project is a simple Library Management System built using Java. The system utilizes various Java Collections like `HashMap` and `ArrayList` to manage the library's data, including members, books, and transactions. The system supports several key operations, including member registration, membership cancellation, book search, book borrowing, and book return with a fine system for overdue returns.

## Features

- **Member Registration**: Allows new members to register with the library.
- **Membership Cancellation**: Members can cancel their library membership.
- **Book Search**: Users can search for books by title, author, or ISBN.
- **Book Borrowing**: Registered members can borrow books from the library.
- **Book Return**: Members can return borrowed books. The system calculates and applies fines for overdue returns.

## Collections Used

- **HashMap**: Used to store members and books data, allowing efficient lookups by key.
- **ArrayList**: Used to maintain lists of borrowed books and transaction records.

## Operations

1. **Member Registration**
   - Adds a new member to the library system.
   - Members are stored in a `HashMap` for quick access.

2. **Membership Cancellation**
   - Removes an existing member from the system.
   - The member’s borrowing history is archived before cancellation.

3. **Book Search**
   - Searches for books based on different criteria (e.g., title, author, ISBN).
   - Books are stored in a `HashMap`, enabling fast retrieval.

4. **Book Borrowing**
   - Members can borrow available books.
   - A borrowed book is recorded in an `ArrayList` along with the borrowing date.

5. **Book Return**
   - Upon returning a book, the system checks if the return is overdue.
   - If overdue, a fine is calculated based on the number of days late.

## Fine System

- **Overdue Fine**: If a book is not returned within the allowed borrowing period, the system calculates a fine. The fine is added to the member’s account and must be paid before further borrowing.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** installed on your system.
- A basic understanding of Java and object-oriented programming.

### Installation

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   ```

2. Compile the Java files:
   ```bash
   javac *.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

### Usage

- Follow the prompts in the console to navigate through the various operations of the library system.
- Register as a member, borrow and return books, and explore the system’s capabilities.

## Contributing

Contributions are welcome! Please fork this repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or issues, please contact [your.email@example.com](mailto:eshwarapandiyan2003@gmail.com).

---

This `README.md` file provides a clear overview of your project, including its features, usage, and setup instructions. You can customize the links and contact information as needed.
