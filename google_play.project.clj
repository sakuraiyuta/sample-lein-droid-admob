(defproject google-play-services_lib/google-play-services_lib "6.1.71-000"
  :description "FIXME: Android library description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"

  :source-paths ["src/clojure" "src"]
  :java-source-paths ["src/java" "gen"]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]

  :dependencies [[org.clojure-android/clojure "1.6.0-RC1"]]

  :profiles {:release {:build-type :release}}
  :android {:target-version "9"
            :library true})

