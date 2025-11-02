package app;

import model.Article;
import newsagency.NewsAgency;
import observer.Subscriber;
import strategy.*;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║         News Portal - Design Patterns Demo                 ║");
        System.out.println("║   Observer + Strategy + Builder + Decorator + Singleton    ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

        NewsAgency newsAgency = NewsAgency.getInstance();

        System.out.println("Creating subscribers:");

        Subscriber rakhman = Subscriber.builder()
                .name("Rakhman Seidigali")
                .email("242171@astanaitedu.kz")
                .phone("+7-700-962-2073")
                .strategy(new EmailNotification())
                .subject(newsAgency)
                .build();

        Subscriber alex = Subscriber.builder()
                .name("Alex Turner")
                .email("articmonkeys@email.com")
                .phone("+1-234-445-566")
                .strategy(new SMSNotification())
                .subject(newsAgency)
                .build();

        Subscriber kyzbolsyn = Subscriber.builder()
                .name("Kyzbolsyn Lexusovich")
                .email("kyzbolssynn234@email.kz")
                .phone("+1-234-567-8899")
                .strategy(new PushNotification())
                .subject(newsAgency)
                .build();

        Subscriber elon = Subscriber.builder()
                .name("Elon Musk")
                .email("technoking@tesla.com")
                .phone("+1-420-690-0000")
                .strategy(new PushNotification())
                .subject(newsAgency)
                .build();

        Subscriber shrek = Subscriber.builder()
                .name("Shrek Ogre")
                .email("shrek@swamp.com")
                .phone("+44-777-OGRE-123")
                .strategy(new SMSNotification())
                .subject(newsAgency)
                .build();

        Subscriber patrick = Subscriber.builder()
                .name("Patrick Star")
                .email("patrick@bikinibottom.sea")
                .phone("+1-555-ROCK-HOME")
                .strategy(new TeaserDecorator(new EmailNotification()))
                .subject(newsAgency)
                .build();

        Subscriber trevor = Subscriber.builder()
                .name("Trevor Phillips")
                .email("trevor@gtamail.com")
                .phone("+1-747-CHAOS-NOW")
                .strategy(new DndDecorator(new PushNotification(), LocalTime.of(22, 0), LocalTime.of(8, 0)))
                .subject(newsAgency)
                .build();

        System.out.println("Subscribing users:");
        rakhman.subscribe();
        rakhman.subscribeCategory("Education");
        rakhman.subscribeCategory("Weather");
        System.out.println("   - Rakhman: Education, Weather");

        alex.subscribe();
        alex.subscribeCategory("Music");
        System.out.println("   - Alex: Music");

        kyzbolsyn.subscribe();
        kyzbolsyn.subscribeCategory("Politics");
        System.out.println("   - Kyzbolsyn: Politics");

        elon.subscribe();
        elon.subscribeCategory("Technology");
        elon.subscribeCategory("Space");
        System.out.println("   - Elon: Technology, Space");

        shrek.subscribe();
        shrek.subscribeCategory("Entertainment");
        shrek.subscribeCategory("Food");
        System.out.println("   - Shrek: Entertainment, Food");

        patrick.subscribe();
        patrick.subscribeCategory("Entertainment");
        patrick.subscribeCategory("Food");
        System.out.println("   - Patrick: Entertainment, Food (Teaser mode - preview only)");

        trevor.subscribe();
        trevor.subscribeCategory("Crime");
        trevor.subscribeCategory("Politics");
        System.out.println("   - Trevor: Crime, Politics (DND 22:00-08:00)");
        System.out.println("   Total: 7 subscribers");

        newsAgency.publish(Article.builder()
                .title("New Education Reforms Announced")
                .content("The government has announced new reforms.")
                .category("Education")
                .author("Ministry of Education")
                .priority(8)
                .build());

        newsAgency.publish(Article.builder()
                .title("Astana Weather: Cold Wave Coming")
                .content("Temperature will drop to -25°C.")
                .category("Weather")
                .author("Kazhydromet")
                .priority(10)
                .build());

        newsAgency.publish(Article.builder()
                .title("Twitter Renamed to X... Again")
                .content("Elon chaos continues.")
                .category("Technology")
                .author("Tech Crunch")
                .priority(7)
                .build());

        newsAgency.publish(Article.builder()
                .title("SpaceX Pizza Delivery to Mars")
                .content("6-8 months delivery time.")
                .category("Space")
                .author("Space News")
                .priority(9)
                .build());

        newsAgency.publish(Article.builder()
                .title("Onions Have Layers")
                .content("Shrek was right all along.")
                .category("Food")
                .author("Food Science Journal")
                .priority(10)
                .build());

        newsAgency.publish(Article.builder()
                .title("SpongeBob Employee of Month Again")
                .content("Patrick celebrates by doing absolutely nothing productive. Classic Patrick. Meanwhile, Squidward is still waiting for his turn to win the award, which seems unlikely given his lack of enthusiasm for customer service at the Krusty Krab.")
                .category("Entertainment")
                .author("Bikini Bottom News")
                .priority(5)
                .build());

        newsAgency.publish(Article.builder()
                .title("Complete Guide to Living in a Swamp")
                .content("Living in a swamp requires dedication, patience, and a love for solitude. First, you need to find the perfect location with adequate mud coverage and minimal foot traffic from annoying fairy tale creatures. Second, establish clear boundaries with 'KEEP OUT' signs. Third, maintain a balanced diet of eyeball soup and weedrat stew. Fourth, develop a thick skin for dealing with unwanted visitors like talking donkeys. Fifth, never underestimate the importance of good hygiene, even in a swamp. Remember, layers are important - just like onions!")
                .category("Entertainment")
                .author("Shrek's Swamp Living Magazine")
                .priority(8)
                .build());

        newsAgency.publish(Article.builder()
                .title("Man Steals Military Jet")
                .content("Last seen heading to Los Santos.")
                .category("Crime")
                .author("LSPD")
                .priority(10)
                .build());

        System.out.println("\n>> Changing strategy:");
        rakhman.setStrategy(new SMSNotification());

        newsAgency.publish(Article.builder()
                .title("Ukraine Conflict Update")
                .content("International support remains strong.")
                .category("Politics")
                .author("BBC News")
                .priority(9)
                .build());

        System.out.println("\n Alex unsubscribes");
        alex.unsubscribe();
        System.out.println("   Total: 6 subscribers");

        newsAgency.publish(Article.builder()
                .title("Megadeth New Album Released")
                .content("The Sick, the Dying... and the Dead!")
                .category("Music")
                .author("Megadeth Official")
                .priority(6)
                .build());

        newsAgency.publish(Article.builder()
                .title("Rock Confirms It Lives Under Patrick")
                .content("In a shocking revelation, Rock has confirmed that it indeed lives under Patrick Star's rock in Bikini Bottom. This discovery has sent waves through the scientific community, as researchers had long speculated about the existence of sentient rock formations. Rock stated, 'It's been a quiet life, but I'm glad to finally come out and share my story with the world. Living under Patrick has its perks, especially when it comes to avoiding the hustle and bustle of Bikini Bottom.' Experts are now keen to study Rock's unique lifestyle and its implications for geology and marine biology.")
                .category("Entertainment")
                .author("Rock Reporter")
                .priority(1)
                .build());

    }
}

