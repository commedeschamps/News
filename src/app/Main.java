package app;

import decorators.DndDecorator;
import decorators.TeaserDecorator;
import facade.NewsFacade;
import factory.ArticleFactory;
import observer.Subscriber;
import strategy.*;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        NewsFacade facade = NewsFacade.usingSingletonAgency();

        System.out.println("Creating subscribers:");

        Subscriber rakhman = facade.register(
                "Rakhman Seidigali",
                "242171@astanaitedu.kz",
                "+7-700-962-2073",
                new EmailNotification()
        );

        Subscriber alex = facade.register(
                "Alex Turner",
                "articmonkeys@email.com",
                "+1-234-445-566",
                new SMSNotification()
        );

        Subscriber kyzbolsyn = facade.register(
                "Kyzbolsyn Lexusovich",
                "kyzbolssynn234@email.kz",
                "+1-234-567-8899",
                new PushNotification()
        );

        Subscriber elon = facade.register(
                "Elon Musk",
                "technoking@tesla.com",
                "+1-420-690-0000",
                new PushNotification()
        );

        Subscriber shrek = facade.register(
                "Shrek Ogre",
                "shrek@swamp.com",
                "+44-777-123-0123",
                new SMSNotification()
        );

        Subscriber patrick = facade.register(
                "Patrick Star",
                "patrick@bikinibottom.sea",
                "+1-555-ROCK-HOME",
                new TeaserDecorator(new EmailNotification())
        );

        Subscriber trevor = facade.register(
                "Trevor Phillips",
                "trevor@gtamail.com",
                "+1-747-CHAOS-NOW",
                new DndDecorator(new PushNotification(), LocalTime.of(22, 0), LocalTime.of(8, 0))
        );

        System.out.println("Subscribing users:");
        facade.addInterest(rakhman, "Education");
        facade.addInterest(rakhman, "Weather");
        System.out.println("   - Rakhman: Education, Weather");

        facade.addInterest(alex, "Music");
        System.out.println("   - Alex: Music");

        facade.addInterest(kyzbolsyn, "Politics");
        System.out.println("   - Kyzbolsyn: Politics");

        facade.addInterest(elon, "Technology");
        facade.addInterest(elon, "Space");
        System.out.println("   - Elon: Technology, Space");

        facade.addInterest(shrek, "Entertainment");
        facade.addInterest(shrek, "Food");
        System.out.println("   - Shrek: Entertainment, Food");

        facade.addInterest(patrick, "Entertainment");
        facade.addInterest(patrick, "Food");
        System.out.println("   - Patrick: Entertainment, Food (Teaser mode - preview only)");

        facade.addInterest(trevor, "Crime");
        facade.addInterest(trevor, "Politics");
        System.out.println("   - Trevor: Crime, Politics (DND 22:00-08:00)");
        System.out.println("   Total: 7 subscribers");

        facade.publish(ArticleFactory.createEducationArticle(
                "New Education Reforms Announced",
                "The government has announced new reforms.",
                "Ministry of Education"
        ));

        facade.publish(ArticleFactory.createWeatherArticle(
                "Astana Weather: Cold Wave Coming",
                "Temperature will drop to -25°C.",
                "Kazhydromet"
        ));

        facade.publish(ArticleFactory.createTechnologyArticle(
                "Twitter Renamed to X... Again",
                "Elon chaos continues.",
                "Tech Crunch"
        ));

        facade.publish(ArticleFactory.createSpaceArticle(
                "SpaceX Pizza Delivery to Mars",
                "6-8 months delivery time.",
                "Space News"
        ));

        facade.publish(ArticleFactory.createFoodArticle(
                "Onions Have Layers",
                "Shrek was right all along.",
                "Food Science Journal"
        ));

        facade.publish(ArticleFactory.createEntertainmentArticle(
                "SpongeBob Employee of Month Again",
                "Patrick celebrates by doing absolutely nothing productive. Classic Patrick. Meanwhile, Squidward is still waiting for his turn to win the award, which seems unlikely given his lack of enthusiasm for customer service at the Krusty Krab.",
                "Bikini Bottom News"
        ));

        facade.publish(ArticleFactory.createEntertainmentArticle(
                "Complete Guide to Living in a Swamp",
                "Living in a swamp requires dedication, patience, and a love for solitude. First, you need to find the perfect location with adequate mud coverage and minimal foot traffic from annoying fairy tale creatures. Second, establish clear boundaries with 'KEEP OUT' signs. Third, maintain a balanced diet of eyeball soup and weedrat stew. Fourth, develop a thick skin for dealing with unwanted visitors like talking donkeys. Fifth, never underestimate the importance of good hygiene, even in a swamp. Remember, layers are important - just like onions!",
                "Shrek's Swamp Living Magazine"
        ));

        facade.publish(ArticleFactory.createCrimeArticle(
                "Man Steals Military Jet",
                "Last seen heading to Los Santos.",
                "LSPD"
        ));

        System.out.println("\n>> Changing strategy:");
        facade.changeStrategy(rakhman, new SMSNotification());

        facade.publish(ArticleFactory.createPoliticsArticle(
                "Ukraine Conflict Update",
                "International support remains strong.",
                "BBC News"
        ));

        System.out.println("\n Alex unsubscribes");
        facade.unsubscribe(alex);
        System.out.println("   Total: 6 subscribers");

        facade.publish(ArticleFactory.createMusicArticle(
                "Arctic Monkeys New Album Released",
                "Whatever People Say I Am, That's What I'm Not is out now.",
                "Arctic Monkeys Official"
        ));

        facade.publish(ArticleFactory.createEntertainmentArticle(
                "Rock Confirms It Lives Under Patrick",
                "In a shocking revelation, Rock has confirmed that it indeed lives under Patrick Star's rock in Bikini Bottom. This discovery has sent waves through the scientific community, as researchers had long speculated about the existence of sentient rock formations. Rock stated, 'It's been a quiet life, but I'm glad to finally come out and share my story with the world. Living under Patrick has its perks, especially when it comes to avoiding the hustle and bustle of Bikini Bottom.' Experts are now keen to study Rock's unique lifestyle and its implications for geology and marine biology.",
                "Rock Reporter"
        ));

        System.out.println("\n>> Decorators demo:");
        System.out.println("Apply TeaserDecorator over PushNotification for Elon (Technology)");
        facade.changeStrategy(elon, new TeaserDecorator(new PushNotification()));
        facade.publish(ArticleFactory.createTechnologyArticle(
                "Starlink v3 Launched with Laser Mesh",
                "SpaceX announces Starlink v3 satellites with inter-satellite laser mesh for ultra-low latency backhaul across continents.",
                "SpaceX PR"
        ));

        System.out.println("\nApply TeaserDecorator over SMS for Shrek (Food)");
        facade.changeStrategy(shrek, new TeaserDecorator(new SMSNotification()));
        facade.publish(ArticleFactory.createFoodArticle(
                "Swamp Onion Soup Recipe",
                "A hearty swamp-style onion soup with eyeball garnish and weedrat croutons for that authentic ogre flavor. Serve hot in a mud bowl.",
                "Shrek's Kitchen"
        ));
    }
}
