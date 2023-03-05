# Bookman Discord Bot

Bookman Discord Bot is a powerful bot that collects information from the Riot API and returns a supercharged profile image with different data. With Bookman, you can show off your League of Legends stats and rank in style, all while hanging out with friends on Discord.

Bookman's advanced features include the ability to analyze match history, display detailed stats for each champion played, and more. Plus, with support for multiple regions and languages, Bookman is perfect for players from all around the world.

With Bookman, you can:

- [ ] Display detailed stats for each champion played 📊 🏗️
- [ ] Analyze match history and performance trends 📈 🏗️
- [ ] Show off your rank and League of Legends stats in style 🏆 ❌
- [ ] Customize your profile image with different backgrounds and icons 🎨 🏗️
- [ ] Support multiple regions and languages 🌎🌍🌏❌

Whether you're a casual player or a hardcore pro, Bookman Discord Bot is the ultimate way to show off your League of Legends skills and impress your friends. So why wait? Add Bookman to your Discord server today and start dominating the competition!

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

1. Create a `.env` file in the root directory of your project by copying the `.env.example` file.
 
```bash
cp .env.example .env
```
2. Fill in the variables in your `.env` file with the appropriate values.
3. Run the application in dev mode.
```shell script
./mvnw compile quarkus:dev
```
# Configuring API Keys and URLs

To use this project, you will need to configure the following variables in your environment:

- `API_KEY`: Your Riot Games API key.
- `SUMMONER_V4_URL`: The URL for the Summoner V4 endpoint.
- `REGION_URL`: The URL for the region endpoint.
- `DATA_DRAGON_URL`: The URL for the Data Dragon endpoint.
- `MATCH_ANALYSIS_COUNT`: The number of matches to analyze.


## Riot Games API Key

To get an API key for the Riot Games API, you will need to follow these steps:

1. Go to the [Riot Games Developer Portal](https://developer.riotgames.com/) and sign in with your Riot Games account.

2. Create a new application by clicking the "Create New Application" button.

3. Fill in the application details, including the name and description of your application.

4. Under the "Production API Key" section, click the "Generate Key" button to generate a new API key.

5. Copy the generated API key and paste it into the `API_KEY` variable in your environment.

## URLs

The `SUMMONER_V4_URL`, `REGION_URL`, and `DATA_DRAGON_URL` variables are pre-configured with the default URLs for these endpoints. However, if you need to change them, you can do so by modifying the values of these variables in your environment.

## Discord Bot

To use the Discord bot, you will need to create a new Discord bot and obtain an API key. Here's how you can do it:

1. Go to the [Discord Developer Portal](https://discord.com/developers/applications) and sign in with your Discord account.

2. Create a new application by clicking the "New Application" button.

3. Give your application a name and click the "Create" button.

4. In the "Bot" section, click the "Add Bot" button to create a new bot.

5. Customize your bot's settings, such as its name and avatar.

6. Copy the API key for your bot and paste it into the `DISCORD_API_KEY` variable in your environment.

7. Invite your bot to your Discord server by going to the "OAuth2" section and selecting the "bot" scope.

8. Finally, copy the generated URL and paste it into your browser to add the bot to your Discord server.

That's it! With these variables, you should be able to use this project and start analyzing League of Legends matches and sending messages through Discord. If you have more questions, don't hesitate to ask.


## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/bookmandiscordbot-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

