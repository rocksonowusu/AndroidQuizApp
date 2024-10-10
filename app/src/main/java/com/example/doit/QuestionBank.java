package com.example.doit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionBank {
    public static List<Question> getQuestions(String category){
        List<Question> questions = new ArrayList<>();

        switch (category)
        {
            case "Animals":
                questions.add(new Question("What is the largest land animal?", Arrays.asList("Elephant", "Lion", "Whale", "Bear"), "Elephant"));
                questions.add(new Question("What is the fastest bird in the world?", Arrays.asList("Owl", "Eagle", "Sparrow", "Peregrine Falcon"), "Peregrine Falcon"));
                questions.add(new Question("Which mammal has the longest gestation period?", Arrays.asList("Elephant", "Whale", "Giraffe", "Rhino"), "Elephant"));
                questions.add(new Question("What is the largest species of shark?", Arrays.asList("Great White Shark", "Hammerhead Shark", "Tiger Shark", "Whale Shark"), "Whale Shark"));
                questions.add(new Question("Which animal is known for its ability to change color?", Arrays.asList("Octopus", "Chameleon", "Squid", "Cuttlefish"), "Chameleon"));
                questions.add(new Question("What is the largest species of bear?", Arrays.asList("Polar Bear", "Grizzly Bear", "Kodiak Bear", "Black Bear"), "Kodiak Bear"));
                questions.add(new Question("Which animal has the longest lifespan?", Arrays.asList("Whale", "Elephant", "Tortoise", "Parrot"), "Tortoise"));
                questions.add(new Question("What is the smallest mammal?", Arrays.asList("Hamster", "Mouse", "Shrew", "Bumblebee Bat"), "Bumblebee Bat"));
                questions.add(new Question("Which animal has the most powerful bite?", Arrays.asList("Shark", "Lion", "Tiger", "Crocodile"), "Crocodile"));
                questions.add(new Question("What is the only flying mammal?", Arrays.asList("Insect", "Flying Squirrel", "Bird", "Bat"), "Bat"));
                break;

            case "Nature":
                questions.add(new Question("What is the longest river in the world?", Arrays.asList("Amazon", "Nile", "Yangtze", "Mississippi"), "Nile"));
                questions.add(new Question("Which is the tallest waterfall in the world?", Arrays.asList("Niagara Falls", "Victoria Falls", "Yosemite Falls", "Angel Falls"), "Angel Falls"));
                questions.add(new Question("Which is the deepest ocean trench in the world?", Arrays.asList("Java Trench", "Puerto Rico Trench", "Mariana Trench", "Tonga Trench"), "Mariana Trench"));
                questions.add(new Question("What is the largest desert in the world?", Arrays.asList("Sahara Desert", "Arabian Desert", "Gobi Desert", "Antarctic Desert"), "Antarctic Desert"));
                questions.add(new Question("Which is the highest mountain in the world?", Arrays.asList("K2", "Mount Everest", "Kangchenjunga", "Lhotse"), "Mount Everest"));
                questions.add(new Question("Which is the largest forest in the world?", Arrays.asList("Amazon Rainforest", "Congo Rainforest", "Taiga", "Daintree Rainforest"), "Amazon Rainforest"));
                questions.add(new Question("What is the most abundant gas in Earth's atmosphere?", Arrays.asList("Oxygen", "Carbon Dioxide", "Nitrogen", "Argon"), "Nitrogen"));
                questions.add(new Question("Which layer of the Earth is the hottest?", Arrays.asList("Mantle", "Core", "Crust", "Lithosphere"), "Core"));
                questions.add(new Question("What is the largest coral reef system in the world?", Arrays.asList("Belize Barrier Reef", "Great Barrier Reef", "New Caledonia Barrier Reef", "Red Sea Coral Reef"), "Great Barrier Reef"));
                questions.add(new Question("What is the name of the largest volcano on Earth?", Arrays.asList("Mount St. Helens", "Mount Etna", "Mount Vesuvius", "Mauna Loa"), "Mauna Loa"));
                break;

            case "Fashion":
                questions.add(new Question("Who is known as the 'Father of Haute Couture'?", Arrays.asList("Coco Chanel", "Giorgio Armani", "Yves Saint Laurent", "Charles Frederick Worth"), "Charles Frederick Worth"));
                questions.add(new Question("What fashion house is known for its iconic 'Red Bottom' shoes?", Arrays.asList("Gucci", "Christian Louboutin", "Prada", "Louis Vuitton"), "Christian Louboutin"));
                questions.add(new Question("Which designer is known for the wrap dress?", Arrays.asList("Donna Karan", "Vera Wang", "Diane von Furstenberg", "Carolina Herrera"), "Diane von Furstenberg"));
                questions.add(new Question("What does 'prêt-à-porter' mean?", Arrays.asList("Custom made", "One size fits all", "High fashion", "Ready to wear"), "Ready to wear"));
                questions.add(new Question("Which fashion brand is known for its check pattern?", Arrays.asList("Versace", "Chanel", "Givenchy", "Burberry"), "Burberry"));
                questions.add(new Question("What year did New York Fashion Week start?", Arrays.asList("1950", "1960", "1975", "1943"), "1943"));
                questions.add(new Question("Who was the first black woman to appear on the cover of Vogue?", Arrays.asList("Naomi Campbell", "Tyra Banks", "Grace Jones", "Beverly Johnson"), "Beverly Johnson"));
                questions.add(new Question("Which designer created the 'Little Black Dress'?", Arrays.asList("Christian Dior", "Jean Patou", "Elsa Schiaparelli", "Coco Chanel"), "Coco Chanel"));
                questions.add(new Question("Which city is known as the fashion capital of the world?", Arrays.asList("New York", "Milan", "London", "Paris"), "Paris"));
                questions.add(new Question("Who is the creative director of Louis Vuitton as of 2024?", Arrays.asList("Virgil Abloh", "Marc Jacobs", "Kim Jones", "Nicolas Ghesquière"), "Nicolas Ghesquière"));
                break;

            case "Trends":
                questions.add(new Question("Which social media platform introduced Stories first?", Arrays.asList("Instagram", "Facebook", "Twitter", "Snapchat"), "Snapchat"));
                questions.add(new Question("What is the most downloaded app of all time?", Arrays.asList("WhatsApp", "Facebook", "Instagram", "TikTok"), "TikTok"));
                questions.add(new Question("Which challenge involved dumping ice water over one's head?", Arrays.asList("Mannequin Challenge", "Harlem Shake", "Planking", "Ice Bucket Challenge"), "Ice Bucket Challenge"));
                questions.add(new Question("Which platform popularized short, looping videos?", Arrays.asList("TikTok", "Instagram Reels", "YouTube Shorts", "Vine"), "Vine"));
                questions.add(new Question("What does 'YOLO' stand for?", Arrays.asList("You Obviously Love Oreos", "Your Opinion Lacks Originality", "Young Optimistic Learning Opportunity", "You Only Live Once"), "You Only Live Once"));
                questions.add(new Question("Which trend involved posing motionless in public places?", Arrays.asList("Flash Mob", "Harlem Shake", "Planking", "Mannequin Challenge"), "Mannequin Challenge"));
                questions.add(new Question("Which app is known for its disappearing messages?", Arrays.asList("WhatsApp", "Facebook Messenger", "Signal", "Snapchat"), "Snapchat"));
                questions.add(new Question("What year did the 'Harlem Shake' go viral?", Arrays.asList("2010", "2015", "2018", "2013"), "2013"));
                questions.add(new Question("What does 'FOMO' stand for?", Arrays.asList("Fun Over Mediocre Options", "Fear of Messing Up", "Friends Over Money", "Fear of Missing Out"), "Fear of Missing Out"));
                questions.add(new Question("Which app became famous for dance challenges?", Arrays.asList("Instagram", "Twitter", "Facebook", "TikTok"), "TikTok"));
                break;

            case "Business":
                questions.add(new Question("Who is the founder of Microsoft?", Arrays.asList("Steve Jobs", "Mark Zuckerberg", "Elon Musk", "Bill Gates"), "Bill Gates"));
                questions.add(new Question("What does IPO stand for?", Arrays.asList("Internal Product Offering", "International Purchase Order", "Investment Portfolio Option", "Initial Public Offering"), "Initial Public Offering"));
                questions.add(new Question("Which company is known as 'Big Blue'?", Arrays.asList("Intel", "Microsoft", "Dell", "IBM"), "IBM"));
                questions.add(new Question("Which country has the largest economy in the world?", Arrays.asList("China", "Japan", "Germany", "United States"), "United States"));
                questions.add(new Question("What year was Amazon founded?", Arrays.asList("1990", "2000", "2005", "1994"), "1994"));
                questions.add(new Question("Who is the CEO of Tesla as of 2024?", Arrays.asList("Tim Cook", "Jeff Bezos", "Satya Nadella", "Elon Musk"), "Elon Musk"));
                questions.add(new Question("Which company is known for the slogan 'Just Do It'?", Arrays.asList("Adidas", "Puma", "Reebok", "Nike"), "Nike"));
                questions.add(new Question("What is the stock ticker symbol for Apple Inc.?", Arrays.asList("APPL", "APPLE", "AAP", "AAPL"), "AAPL"));
                questions.add(new Question("Who is considered the father of modern economics?", Arrays.asList("John Maynard Keynes", "Milton Friedman", "David Ricardo", "Adam Smith"), "Adam Smith"));
                questions.add(new Question("Which company is the parent of Google?", Arrays.asList("Microsoft", "Facebook", "Amazon", "Alphabet"), "Alphabet"));
                break;

            case "Science":
                questions.add(new Question("What is the chemical symbol for gold?", Arrays.asList("Ag", "Gd", "Go", "Au"), "Au"));
                questions.add(new Question("Who developed the theory of relativity?", Arrays.asList("Isaac Newton", "Galileo Galilei", "Nikola Tesla", "Albert Einstein"), "Albert Einstein"));
                questions.add(new Question("What is the powerhouse of the cell?", Arrays.asList("Nucleus", "Ribosome", "Endoplasmic Reticulum", "Mitochondria"), "Mitochondria"));
                questions.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("Jupiter", "Venus", "Saturn", "Mars"), "Mars"));
                questions.add(new Question("What is the speed of light?", Arrays.asList("150,000,000 meters per second", "100,000,000 meters per second", "50,000,000 meters per second", "299,792,458 meters per second"), "299,792,458 meters per second"));
                questions.add(new Question("Who is known as the father of modern physics?", Arrays.asList("Isaac Newton", "Albert Einstein", "Niels Bohr", "Galileo Galilei"), "Galileo Galilei"));
                questions.add(new Question("What is the most abundant element in the universe?", Arrays.asList("Oxygen", "Carbon", "Nitrogen", "Hydrogen"), "Hydrogen"));
                questions.add(new Question("What type of bond involves the sharing of electron pairs between atoms?", Arrays.asList("Ionic bond", "Metallic bond", "Hydrogen bond", "Covalent bond"), "Covalent bond"));
                questions.add(new Question("What is the study of mushrooms called?", Arrays.asList("Mycology", "Bryology", "Phycology", "Lichenology"), "Mycology"));
                questions.add(new Question("What is the first element on the periodic table?", Arrays.asList("Oxygen", "Carbon", "Helium", "Hydrogen"), "Hydrogen"));
                break;

            case "Food":
                questions.add(new Question("Which country is known for sushi?", Arrays.asList("China", "South Korea", "Vietnam", "Japan"), "Japan"));
                questions.add(new Question("What is the main ingredient in guacamole?", Arrays.asList("Tomato", "Onion", "Pepper", "Avocado"), "Avocado"));
                questions.add(new Question("Which cheese is traditionally used on a Greek salad?", Arrays.asList("Mozzarella", "Cheddar", "Parmesan", "Feta"), "Feta"));
                questions.add(new Question("What is the most expensive spice in the world?", Arrays.asList("Cinnamon", "Vanilla", "Clove", "Saffron"), "Saffron"));
                questions.add(new Question("Which country is the origin of the cocktail Mojito?", Arrays.asList("Brazil", "Mexico", "Spain", "Cuba"), "Cuba"));
                questions.add(new Question("What type of pastry is used to make a baklava?", Arrays.asList("Puff pastry", "Shortcrust pastry", "Phyllo pastry", "Choux pastry"), "Phyllo pastry"));
                questions.add(new Question("What is the main ingredient in hummus?", Arrays.asList("Lentils", "Beans", "Peas", "Chickpeas"), "Chickpeas"));
                questions.add(new Question("Which fruit has its seeds on the outside?", Arrays.asList("Apple", "Banana", "Kiwi", "Strawberry"), "Strawberry"));
                questions.add(new Question("Which country is known for the dish paella?", Arrays.asList("Portugal", "Italy", "Greece", "Spain"), "Spain"));
                questions.add(new Question("What is the primary ingredient in a traditional French croissant?", Arrays.asList("Milk", "Sugar", "Butter", "Eggs"), "Butter"));
                break;

            case "Cars":
                questions.add(new Question("Which company is known for its 'quattro' all-wheel-drive system?", Arrays.asList("BMW", "Mercedes-Benz", "Toyota", "Audi"), "Audi"));
                questions.add(new Question("What is the best-selling car model of all time?", Arrays.asList("Ford F-Series", "Honda Civic", "Volkswagen Beetle", "Toyota Corolla"), "Toyota Corolla"));
                questions.add(new Question("Which car manufacturer produces the 'Mustang'?", Arrays.asList("Chevrolet", "Dodge", "Ford", "Nissan"), "Ford"));
                questions.add(new Question("What does 'SUV' stand for?", Arrays.asList("Sport Ultimate Vehicle", "Speed Utility Vehicle", "Sports Utility Vehicle", "Special Utility Vehicle"), "Sports Utility Vehicle"));
                questions.add(new Question("Which car brand has a prancing horse as its logo?", Arrays.asList("Porsche", "Lamborghini", "Ferrari", "Maserati"), "Ferrari"));
                questions.add(new Question("What is the top speed of the Bugatti Chiron?", Arrays.asList("200 mph", "250 mph", "300 mph", "350 mph"), "300 mph"));
                questions.add(new Question("Which car company produces the 'Model S'?", Arrays.asList("Nissan", "BMW", "Audi", "Tesla"), "Tesla"));
                questions.add(new Question("What does the acronym 'ABS' stand for in automotive terms?", Arrays.asList("Automatic Braking System", "Auto Balance System", "Automatic Bypass System", "Anti-lock Braking System"), "Anti-lock Braking System"));
                questions.add(new Question("Which company owns Lamborghini?", Arrays.asList("Fiat", "Ford", "BMW", "Volkswagen"), "Volkswagen"));
                questions.add(new Question("Which country is known for the luxury brand Rolls-Royce?", Arrays.asList("Germany", "France", "Italy", "United Kingdom"), "United Kingdom"));
                break;

            case "Bible":
                questions.add(new Question("Who built the ark to survive the Great Flood?", Arrays.asList("Moses", "Abraham", "David", "Noah"), "Noah"));
                questions.add(new Question("Which book of the Bible tells the story of creation?", Arrays.asList("Exodus", "Leviticus", "Deuteronomy", "Genesis"), "Genesis"));
                questions.add(new Question("Who was swallowed by a great fish?", Arrays.asList("Daniel", "Joseph", "Jonah", "Samson"), "Jonah"));
                questions.add(new Question("Which king built the first temple in Jerusalem?", Arrays.asList("David", "Saul", "Solomon", "Rehoboam"), "Solomon"));
                questions.add(new Question("What is the last book of the New Testament?", Arrays.asList("Acts", "James", "Jude", "Revelation"), "Revelation"));
                questions.add(new Question("Who led the Israelites out of Egypt?", Arrays.asList("Joshua", "Aaron", "Caleb", "Moses"), "Moses"));
                questions.add(new Question("What is the shortest book in the New Testament?", Arrays.asList("2 John", "3 John", "Jude", "Philemon"), "3 John"));
                questions.add(new Question("How many days and nights did Jesus fast in the wilderness?", Arrays.asList("20", "30", "40", "50"), "40"));
                questions.add(new Question("Who was the first man created by God?", Arrays.asList("Abel", "Cain", "Seth", "Adam"), "Adam"));
                questions.add(new Question("Who is considered the father of many nations?", Arrays.asList("Isaac", "Jacob", "Joseph", "Abraham"), "Abraham"));
                break;

            case "Space":
                questions.add(new Question("Which planet is known as the Earth's twin?", Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"), "Venus"));
                questions.add(new Question("What is the name of the galaxy we live in?", Arrays.asList("Andromeda", "Whirlpool", "Sombrero", "Milky Way"), "Milky Way"));
                questions.add(new Question("What is the closest star to Earth?", Arrays.asList("Sirius", "Alpha Centauri", "Betelgeuse", "Proxima Centauri"), "Proxima Centauri"));
                questions.add(new Question("Which planet is known for its rings?", Arrays.asList("Uranus", "Neptune", "Jupiter", "Saturn"), "Saturn"));
                questions.add(new Question("What is the largest planet in our solar system?", Arrays.asList("Neptune", "Earth", "Uranus", "Jupiter"), "Jupiter"));
                questions.add(new Question("Who was the first human to travel into space?", Arrays.asList("Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "John Glenn"), "Yuri Gagarin"));
                questions.add(new Question("What is the name of the first artificial satellite?", Arrays.asList("Explorer 1", "Luna 2", "Sputnik 1", "Vostok 1"), "Sputnik 1"));
                questions.add(new Question("What is the most abundant element in the universe?", Arrays.asList("Helium", "Oxygen", "Carbon", "Hydrogen"), "Hydrogen"));
                questions.add(new Question("Which planet is known for having the Great Red Spot?", Arrays.asList("Saturn", "Uranus", "Neptune", "Jupiter"), "Jupiter"));
                questions.add(new Question("Which planet has the most moons?", Arrays.asList("Saturn", "Earth", "Mars", "Venus"), "Saturn"));
                break;

        }
        return questions;
    }
}
