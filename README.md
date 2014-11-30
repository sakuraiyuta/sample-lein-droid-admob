# Sample project for using Admob on lein-droid

## How to build

- check out this repo.
- copy `./google_play.project.clj` to your GooglePlayService lib's directory. ex) `/opt/android-sdk/extras/google/google_play_services/libproject/google-play-services_lib/`
- fix `ads:adUnitId` attribute in `res/layout/admob.xml`
- fix `:project-dependencies` and `:external-classes-paths` in `./project.clj`
- `lein do clean, doall`

If you don't have Admob UnitID, register [here](http://www.google.co.jp/ads/admob/) and get UnitID.

