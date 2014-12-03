# Sample lein-droid project with Admob

## How to build

- install newer version of `lein-droid`. [(tutorial)](https://github.com/clojure-android/lein-droid/wiki/Tutorial)
- clone this repo.
- copy `./google_play.project.clj` to your GooglePlayService lib's directory. ex) `/opt/android-sdk/extras/google/google_play_services/libproject/google-play-services_lib/`
- fix `:project-dependencies` and `:external-classes-paths` in `./project.clj`
- fix `ads:adUnitId` attribute in `./res/layout/admob.xml` If you don't have Admob UnitID, register [here](http://www.google.co.jp/ads/admob/) and get UnitID.
- `lein do clean, doall` or `lein with-profile release do clean, doall` to build and install.

If you got error like `sdk-path not found`, check your leiningen's personal profiles.

ex) `${HOME}/.lein/profiles.clj`:
- `:android-common` already exists and contains `:android {:sdk-path "..."}`?
```clojure
;; My sample
{:user {:plugins [[lein-pprint "1.1.1"]
                  [lein-kibit "0.0.8"]
                  [lein-droid "0.3.0-beta4"]]}
 :android-user {:plugins [[lein-pprint "1.1.1"]
                          [lein-kibit "0.0.8"]]}
 :android-common {:android {:sdk-path "/opt/android-sdk" ;; <- need, on newer version
                            :keypass "android"
                            :storepass "android"}}}
```

*Notice: If you don't fix `./AndroidManifest.template.xml`, Adview displays an error like "no permission access to internet.", when AdView inflates on release-version.*

## Check point

If you want to use Admob in your project with `lein-droid`, check and fix by these steps:

- add some elements in `./AndroidManifest.template.xml` needs by Admob. (look this sample, commented)
- add Adview element in your layout xml. (maybe you also can add view dynamically)
- add `:project-dependencies` and `:external-classes-paths` in your project's `project.clj`. it makes to found definition of classes like Adview when generating your root project's resources.
- for testing, it's good idea to generate your DeviceID that set to `AdRequest$Builder` by `addTestDevice` method.

## See also

- [Using Google Play Services](https://github.com/clojure-android/lein-droid/issues/87)
- [my gisted description](https://gist.github.com/sakuraiyuta/3cb74a8fb4235084873c)

