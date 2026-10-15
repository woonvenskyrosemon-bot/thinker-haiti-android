THINKER HAÏTI — PROJET D’APPLICATION ANDROID

À SAVOIR
Ce dossier est le projet source Android Studio, pas encore un fichier APK installable.
L’application charge le site en ligne dans une WebView. Le site doit donc d’abord être hébergé
sur une adresse HTTPS avec PHP et MySQL fonctionnels.

ÉTAPES
1. Ouvre le projet dans Android Studio.
2. Dans app/src/main/java/ht/thinkerhaiti/app/MainActivity.kt, remplace :
   https://REMPLACEZ-PAR-VOTRE-DOMAINE/
   par l’adresse HTTPS réelle de ton site, par exemple https://ton-domaine.com/
3. Clique sur Sync Project with Gradle Files.
4. Attends l’installation des composants Android et la synchronisation Gradle.
5. Menu Build > Build Bundle(s) / APK(s) > Build APK(s).
6. Pour un APK partageable, utilise Build > Generate Signed Bundle / APK et crée une clé de signature
   que tu gardes en sécurité.

L’application Android est une enveloppe WebView. Les pages PHP, les formulaires et l’espace admin
sont servis par le site hébergé. Les liens externes comme WhatsApp et Facebook s’ouvrent dans
les applications ou le navigateur correspondants.
