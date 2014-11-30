(ns sample.lein.droid.admob.main
  (:use [neko.activity :only [defactivity *a set-content-view!]]
        [neko.threading :only [on-ui]]
        [neko.resource :only [get-id get-layout]]
        [neko.find-view :only [find-view find-views]]
        [neko.ui :only [make-ui]])
  (:require
    [neko.log :as log]
    [sample.lein.droid.admob.hash :as h])
  (:import
    [com.google.android.gms.ads AdView AdRequest$Builder]))

(defn get-test-device-id
  []
  (let [cr (.getContentResolver (*a))
        android-id (android.provider.Settings$Secure/getString
                     cr
                     "android_id")]
    (-> (h/get-hash-str android-id :MD5)
        clojure.string/upper-case)))

(defn load-ad!
  [activity id]
  ;; HACK: `find-view` fn doesn't work...why?
  ;; Might only be a problem at works in REPL, on neko 3.1.0-beta1.
  (when-let [^AdView ad-view (.findViewById activity (get-id id))]
    (log/i (str "ad-view=" ad-view))
    (let [ad-req-builder (AdRequest$Builder.)]
      (.addTestDevice ad-req-builder (get-test-device-id))
      (.loadAd ad-view (.build ad-req-builder)))))

(defactivity sample.lein.droid.admob.MainActivity
  :on-create
  (fn [this bundle]
    (on-ui
      (set-content-view!
        (*a)
        (get-layout :sample.lein.droid.admob/admob))
      (load-ad! (*a) :sample.lein.droid.admob/ad))))

