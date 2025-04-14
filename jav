<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Showcase</title>
    <!-- Favicon for browser tab - using the file we uploaded -->
    <link rel="icon" href="favicon.jpg" type="image/jpeg">
    <style>
        /* CSS Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            color: #333;
        }

        header {
            background-color: #2c3e50;
            color: white;
            text-align: center;
            padding: 2rem 0;
        }

        header h1 {
            margin-bottom: 0.5rem;
        }

        main {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 1rem;
        }

        .controls {
            display: flex;
            justify-content: center;
            margin-bottom: 2rem;
        }

        button {
            padding: 0.5rem 1rem;
            margin: 0 0.5rem;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #2980b9;
        }

        .car-showcase {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
        }

        .car-card {
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }

        .car-card:hover {
            transform: translateY(-5px);
        }

        .car-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .car-info {
            padding: 1rem;
        }

        .car-name {
            font-size: 1.2rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }

        .car-description {
            color: #666;
            font-size: 0.9rem;
        }

        footer {
            text-align: center;
            padding: 1rem;
            background-color: #2c3e50;
            color: white;
            margin-top: 2rem;
        }

        @media (max-width: 768px) {
            .car-showcase {
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            }
        }

        @media (max-width: 480px) {
            .car-showcase {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <header>
        <h1>Car Showcase</h1>
        <p>Explore our collection of beautiful cars</p>
    </header>
    
    <main>
        <div class="controls">
            <button id="prev-btn">Previous</button>
            <button id="next-btn">Next</button>
        </div>
        
        <div class="car-showcase" id="car-container">
            <!-- Cars will be dynamically added here by JavaScript -->
        </div>
    </main>
    
    <footer>
        <p>&copy; 2023 Car Showcase. All rights reserved.</p>
    </footer>
    
    <script>
        // JavaScript Code
        // Car data - in a real application, this might come from an API
        const cars = [
            {
                id: 1,
                name: "Tesla Model S",
                image: "https://images.unsplash.com/photo-1560958089-b8a1929cea89?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dGVzbGElMjBtb2RlbCUyMHN8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=600&q=60",
                description: "Luxury electric sedan with impressive range and performance."
            },
            {
                id: 2,
                name: "Ford Mustang",
                image: "https://images.unsplash.com/photo-1584345604476-8ec5e12e42dd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Zm9yZCUyMG11c3Rhbmd8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=600&q=60",
                description: "Iconic American muscle car with powerful engine options."
            },
            {
                id: 3,
                name: "Porsche 911",
                image: "https://images.unsplash.com/photo-1580274455191-1c62238fa333?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cG9yc2NoZSUyMDkxMXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=600&q=60",
                description: "Legendary sports car known for its precision engineering."
            },
            {
                id: 4,
                name: "Toyota Supra",
                image: "https://images.unsplash.com/photo-1638618164682-12b986ec2a75?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dG95b3RhJTIwc3VwcmF8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=600&q=60",
                description: "Revived Japanese sports car with BMW-sourced powertrain."
            },
            {
                id: 5,
                name: "Lamborghini Aventador",
                image: "https://images.unsplash.com/photo-1544829099-b9a0c07fad1a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bGFtYm9yZ2hpbmklMjBhdmVudGFkb3J8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=600&q=60",
                description: "Exotic supercar with scissor doors and a V12 engine."
            },
            {
                id: 6,
                name: "Audi R8",
                image: "https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YXVkaSUyMHI4fGVufDB8fDB8fHww&auto=format&fit=crop&w=600&q=60",
                description: "German supercar with everyday usability and quattro AWD."
            }
        ];

        // DOM elements
        const carContainer = document.getElementById('car-container');
        const prevBtn = document.getElementById('prev-btn');
        const nextBtn = document.getElementById('next-btn');

        // Variables for pagination
        let currentPage = 1;
        const carsPerPage = 3;
        const totalPages = Math.ceil(cars.length / carsPerPage);

        // Function to display cars based on current page
        function displayCars() {
            // Clear current display
            carContainer.innerHTML = '';
            
            // Calculate start and end indices for the current page
            const startIndex = (currentPage - 1) * carsPerPage;
            const endIndex = Math.min(startIndex + carsPerPage, cars.length);
            
            // Display cars for the current page
            for (let i = startIndex; i < endIndex; i++) {
                const car = cars[i];
                
                // Create car card element
                const carCard = document.createElement('div');
                carCard.classList.add('car-card');
                
                // Add car content
                carCard.innerHTML = `
                    <img src="${car.image}" alt="${car.name}" class="car-image">
                    <div class="car-info">
                        <h2 class="car-name">${car.name}</h2>
                        <p class="car-description">${car.description}</p>
                    </div>
                `;
                
                // Add car card to container
                carContainer.appendChild(carCard);
            }
            
            // Update button states
            updateButtonStates();
        }

        // Function to update button states (disable/enable based on current page)
        function updateButtonStates() {
            prevBtn.disabled = currentPage === 1;
            nextBtn.disabled = currentPage === totalPages;
            
            // Visual indication of disabled state
            if (prevBtn.disabled) {
                prevBtn.style.opacity = 0.5;
                prevBtn.style.cursor = 'not-allowed';
            } else {
                prevBtn.style.opacity = 1;
                prevBtn.style.cursor = 'pointer';
            }
            
            if (nextBtn.disabled) {
                nextBtn.style.opacity = 0.5;
                nextBtn.style.cursor = 'not-allowed';
            } else {
                nextBtn.style.opacity = 1;
                nextBtn.style.cursor = 'pointer';
            }
        }

        // Event listeners for pagination buttons
        prevBtn.addEventListener('click', () => {
            if (currentPage > 1) {
                currentPage--;
                displayCars();
            }
        });

        nextBtn.addEventListener('click', () => {
            if (currentPage < totalPages) {
                currentPage++;
                displayCars();
            }
        });

        // Initial display
        displayCars();
    </script>
</body>
</html>
